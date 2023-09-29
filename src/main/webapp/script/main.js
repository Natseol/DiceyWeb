function inside() {
    fetch("./servlet", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            // "name" 속성의 값을 전역 변수에 저장
            let nameValue = data.name;
            let ageValue = data.age;        

            // fetch 내부에서 변수를 사용하려면 여기에 추가 작업을 수행하세요.
            // 변수에 저장된 값 출력
            console.log(data);
            console.log(data.player.inventory[0].name);
            console.log(data.script);
            document.getElementsByClassName("script").innerHTML = "data.script";
            console.log("Name Value (inside fetch): " + nameValue);
            console.log("Age Value (inside fetch): " + ageValue);         
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
	
let isSelect = false;
function changeColor() {
    if (isSelect==true) {
        document.getElementById("dice").src="image/dice1.png";
        isSelect = false;
    } else {
        document.getElementById("dice").src="image/dicered1.png";
        isSelect = true;
    }
}

	
