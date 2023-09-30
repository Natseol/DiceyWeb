const buttons = document.querySelectorAll('.jn');
let jobNumber=0;
// 각 버튼에 클릭 이벤트 리스너를 추가합니다.
buttons.forEach((button) => {
  button.addEventListener('click', function(event) {
    // 클릭된 버튼의 data-buttonid 속성을 읽어옵니다.
    const buttonId = event.target.getAttribute('data-buttonid');
    
    // 버튼을 구분할 수 있는 작업을 수행합니다.
    console.log('눌린 버튼의 아이디:', buttonId);
    
    // 버튼 아이디에 따라 다른 동작을 수행할 수 있습니다.
    if (buttonId === '1') {jobNumber=1;      
    } else if (buttonId === '2') {jobNumber=2;      
    } else if (buttonId === '3') {jobNumber=3;
    } else if (buttonId === '4') {jobNumber=4;
    } else if (buttonId === '5') {jobNumber=5;
    }
    jnPost();
  });
});

function jnPost() {
	const postData = {
        jobNum: jobNumber,
        equipmentNum: 'value2'
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
            console.log(data.player.inventory[0].name);
            console.log(data.script);
            document.getElementById("script").innerHTML = data.script;   
        })
        .catch(error => {
            console.error('Error:', error);
    });
}

function inside() {
    fetch("./servlet", {
        method: 'GET',
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

function ins1() {
    document.getElementById("script").innerHTML = "변경";
}
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
    "image/dice10.png",
    "image/dice1.png",
    "image/dice2.png",
    "image/dice3.png",
    "image/dice4.png",
    "image/dice5.png",
    "image/dice6.png",
    "image/dice7.png",
    "image/dice8.png",
    "image/dice9.png"
];

const imageRedPaths = [
    "image/dicered10.png",
    "image/dicered1.png",
    "image/dicered2.png",
    "image/dicered3.png",
    "image/dicered4.png",
    "image/dicered5.png",
    "image/dicered6.png",
    "image/dicered7.png",
    "image/dicered8.png",
    "image/dicered9.png"
];

const imageContainer = document.getElementById("image-container");

const diceList = [2,3,5,6,2];

for (let i = 0; i < diceList.length; i++) {

    let imageElement = document.createElement("img");
    imageElement.className = "dice-image";
    imageElement.src = imagePaths[diceList[i]];
    imageElement.addEventListener("click", function() {
        console.log(imageElement.src);
        console.log(imagePaths[diceList[i]]);
        if (imageElement.src == imagePaths[diceList[i]]) {
            console.log("검은색");
            imageElement.src = imageRedPaths[diceList[i]];
        } else if (imageElement.src == imageRedPaths[diceList[i]]) {
            console.log("빨강");
            imageElement.src = imagePaths[diceList[i]];
        } else {
            console.log("안됨");
        }
    });
    
    imageContainer.appendChild(imageElement);
}

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




	
