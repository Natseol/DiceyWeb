const jobButtons = document.querySelectorAll('.selectjob');
let jobNumber=0;

jobButtons.forEach((button) => {
  button.addEventListener('click', function(event) {
    const buttonId = event.target.getAttribute('jobId');    
    if (buttonId === '1') {jobNumber=1;      
    } else if (buttonId === '2') {jobNumber=2;      
    } else if (buttonId === '3') {jobNumber=3;
    } else if (buttonId === '4') {jobNumber=4;
    } else if (buttonId === '5') {jobNumber=5;
    }
    jobPost();
    changeBorder(jobButtons, button);
  });
});

function changeBorder(buttons, button) {
    buttons.forEach((button) => {
    button.style = "border:0px;";
	})
    button.style = "box-shadow : 0 0 7px 2px black;";
}

const equipmentButtons = document.querySelectorAll('.selectEquipment');
let equipmentNumber=1;

equipmentButtons.forEach((button) => {
  button.addEventListener('click', function(event) {
    const buttonId = event.target.getAttribute('equipmentId');    
    if (buttonId === '1') {equipmentNumber=1;      
    } else if (buttonId === '2') {equipmentNumber=2;      
    }
    jobPost();
    changeBorder(equipmentButtons, button);
  });
});

function jobPost() {
	const postData = {
        jobNum: jobNumber,
        equipmentNum: equipmentNumber
    }

	fetch("./servlet", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(postData)
    })
        .then(response => response.json())
        .then(data => {
 
            // fetch 내부에서 변수를 사용하려면 여기에 추가 작업을 수행하세요.
            // 변수에 저장된 값 출력
            console.log(data);            
            console.log(data.script);
            document.getElementById("script").innerHTML = data.script;
            printInventory(data.player.inventory);
        })
        .catch(error => {
            console.error('Error:', error);
    });
}

function printInventory(inventory) {
    let str="";
    if (inventory[0].name!=null){
        for (let i = 0 ; i < inventory.length ; i++) {
            if (inventory[i].name!="빈슬롯") {
                str += inventory[i].name;
                str += " : ";
                str += inventory[i].description;
                if (inventory[i].count>0) {
                    str += "  [카운트 : "+inventory[i].count+"]";
                }
                str += "<br>";
            }
        }
        document.getElementById("inventory").innerHTML = str;
    }
}

const nextPage = document.getElementById("nextPage");
if (nextPage) {
nextPage.addEventListener('click', function() {
    fetch("./servlet", {
        method: 'GET'
    })          
        .catch(error => {
            console.error('Error:', error);
    });
});
}