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
    printInventory();
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
        })
        .catch(error => {
            console.error('Error:', error);
    });
}

function printInventory() {
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
            let str="";
            if (data.player.inventory[0].name!=null){
	            for (let i = 0 ; i < data.player.inventory.length ; i++) {
	                str += data.player.inventory[i].name;
	                str += " : ";
	                str += data.player.inventory[i].description;
	                str += "<br>";
	            }
	            str += "<br>";
	            document.getElementById("inventory").innerHTML = str;
            }   
        })
        .catch(error => {
            console.error('Error:', error);
    });
}

function inside() {
    fetch("./servlet", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
 
            // fetch 내부에서 변수를 사용하려면 여기에 추가 작업을 수행하세요.
            // 변수에 저장된 값 출력
            console.log(data);
            console.log(data.player.inventory[0].name);
            console.log(data.script);
            document.getElementById("script").innerHTML = data.script;   
        })
        .catch(error => {
            console.error('Error:', error);
    });
};

/* 참고할 것
// JSON 데이터 예제
var json1 = '{"name": "John", "age": 30}';
var json2 = '{"name": "Alice", "age": 25}';

// JSON 데이터를 자바스크립트 객체로 역직렬화
var person1 = JSON.parse(json1);
var person2 = JSON.parse(json2);

// 역직렬화된 객체 사용
console.log("Person 1 Name: " + person1.name);  
console.log("Person 1 Age: " + person1.age);
console.log("Person 2 Name: " + person2.name);
console.log("Person 2 Age: " + person2.age);
*/




	
