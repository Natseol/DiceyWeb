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
    // printInventory();
  });
});

const equipmentButtons = document.querySelectorAll('.selectEquipment');
let equipmentNumber=0;

equipmentButtons.forEach((button) => {
  button.addEventListener('click', function(event) {
    const buttonId = event.target.getAttribute('equipmentId');    
    if (buttonId === '1') {equipmentNumber=1;      
    } else if (buttonId === '2') {equipmentNumber=2;      
    }
    // printInventory();
    jobPost();
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

// function printInventory() {
//     const postData = {
//         jobNum: jobNumber,
//         equipmentNum: equipmentNumber
//     }

//     fetch("./servlet", {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify(postData)
//     })
//         .then(response => response.json())
//         .then(data => {
//             let str="";
//             if (data.player.inventory[0].name!=null){
// 	            for (let i = 0 ; i < data.player.inventory.length ; i++) {
// 	                str += data.player.inventory[i].name;
// 	                str += " : ";
// 	                str += data.player.inventory[i].description;
//                     if (data.player.inventory[i].count>0) {
//                         str += "  [카운트 : "+data.player.inventory[i].count+"]";
//                     }
//                     str += "<br>";
// 	            }
// 	            str += "<br>";
// 	            document.getElementById("inventory").innerHTML = str;
//             }   
//         })
//         .catch(error => {
//             console.error('Error:', error);
//     });
// }

function printInventory(inventory) {
    let str="";
    if (inventory[0].name!=null){
        for (let i = 0 ; i < inventory.length ; i++) {
            str += inventory[i].name;
            str += " : ";
            str += inventory[i].description;
            if (inventory[i].count>0) {
                str += "  [카운트 : "+inventory[i].count+"]";
            }
            str += "<br>";
        }
        str += "<br>";
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