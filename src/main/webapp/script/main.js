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



// 0부터 9까지의 이미지 URL 배열을 정의합니다.
const imagePaths = [
    "http://localhost:8088/DiceyWeb/image/dice10.png",
    "http://localhost:8088/DiceyWeb/image/dice1.png",
    "http://localhost:8088/DiceyWeb/image/dice2.png",
    "http://localhost:8088/DiceyWeb/image/dice3.png",
    "http://localhost:8088/DiceyWeb/image/dice4.png",
    "http://localhost:8088/DiceyWeb/image/dice5.png",
    "http://localhost:8088/DiceyWeb/image/dice6.png",
    "http://localhost:8088/DiceyWeb/image/dice7.png",
    "http://localhost:8088/DiceyWeb/image/dice8.png",
    "http://localhost:8088/DiceyWeb/image/dice9.png"
];

const imageRedPaths = [
    "http://localhost:8088/DiceyWeb/image/dicered10.png",
    "http://localhost:8088/DiceyWeb/image/dicered1.png",
    "http://localhost:8088/DiceyWeb/image/dicered2.png",
    "http://localhost:8088/DiceyWeb/image/dicered3.png",
    "http://localhost:8088/DiceyWeb/image/dicered4.png",
    "http://localhost:8088/DiceyWeb/image/dicered5.png",
    "http://localhost:8088/DiceyWeb/image/dicered6.png",
    "http://localhost:8088/DiceyWeb/image/dicered7.png",
    "http://localhost:8088/DiceyWeb/image/dicered8.png",
    "http://localhost:8088/DiceyWeb/image/dicered9.png"
];

const imageContainer = document.getElementById("image-container");

function setDiceImage(list) {
    imageContainer.innerHTML = "";
    for (let i = 0; i < list.length; i++) {

        let imageElement = document.createElement("img");
        imageElement.className = "dice-image";
        imageElement.src = imagePaths[list[i]];
        imageElement.addEventListener("click", function() {
            console.log(imageElement.src);
            console.log(imagePaths[list[i]]);
            if (imageElement.src == imagePaths[list[i]]) {
                console.log("검은색");
                resetDiceImage(list);
                imageElement.src = imageRedPaths[list[i]];
                
            } else if (imageElement.src == imageRedPaths[list[i]]) {
                console.log("빨강");
                imageElement.src = imagePaths[list[i]];
            } else {
                console.log("안됨");
            }
        });
        imageContainer.appendChild(imageElement);
    }
}
function resetDiceImage(list) {
    for (let i = 0; i < list.length; i++) {
        document.getElementsByClassName("dice-image")[i].src = imagePaths[list[i]];
    }
}

const itemContainer = document.getElementById("item-container");

function setItemList(list) {
    itemContainer.innerHTML = "";
    for (let i = 0; i < list.length; i++) {
        let itemElement = document.createElement("div");
        itemElement.className = "item-div";
        itemElement.innerHTML = list[i].name + " : " + list[i].description;
		itemElement.classList.add("itemBasic");
        
        itemElement.addEventListener("click", function () {
        if (itemElement.classList.contains("itemBasic")) {
            console.log("검은색");
            resetItemColor(list);
            itemElement.classList.remove("itemBasic");
            itemElement.classList.add("itemRed");
        } else if (itemElement.classList.contains("itemRed")) {
            console.log("빨강");
            itemElement.classList.remove("itemRed");
            itemElement.classList.add("itemBasic");
        } else {
            console.log("안됨");
        }
      });

      itemContainer.appendChild(itemElement);
    }
}

function resetItemColor(list) {
    for (let i = 0; i < list.length; i++) {
        document.getElementsByClassName("item-div")[i].classList.remove("itemRed");
        document.getElementsByClassName("item-div")[i].classList.add("itemBasic");
    }
}

function printDice() {
    fetch("./battleserv", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            const diceList = data.myTurn.diceList;

            console.log(diceList);
            console.log(data.myTurn.item);

            setDiceImage(diceList);
            setItemList(data.myTurn.item);

        })
        .catch(error => {
            console.error('Error:', error);
    });
};

function myBattle() {
    fetch("./battleserv", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            const diceList = data.myTurn.diceList;

            console.log(diceList);

            setDiceImage(diceList);

        })
        .catch(error => {
            console.error('Error:', error);
    });
};



function consolPrint() {
    var container = document.getElementById("image-container");
        // 컨테이너 내의 모든 이미지 요소 가져오기
    var images = container.getElementsByTagName('img');

    // 이미지를 찾는 반복문
    for (var i = 0; i < images.length; i++) {
        var currentImageSrc = images[i].src;
        if (imageRedPaths.includes(images[i].src)) {
            // 이미지가 배열에 있는 경우 해당 이미지에 대한 작업을 수행
            console.log('이미지를 찾았습니다:', i);
            // 추가 작업을 수행할 수 있습니다.
        } else {
            console.log('이미지가 없습니다:', i);
        }
    }
}




	
