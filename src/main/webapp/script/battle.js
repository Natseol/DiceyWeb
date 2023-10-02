// 0부터 9까지의 Dice 이미지를 정의합니다.
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

//주사위 그림 생성
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

//적 주사위 그림 생성
const imageContainer2 = document.getElementById("image-container2");
function setDiceImage2(list) {
    imageContainer2.innerHTML = "";
    for (let i = 0; i < list.length; i++) {

        let imageElement = document.createElement("img");
        imageElement.className = "dice-image2";
        imageElement.src = imagePaths[list[i]];        
        imageContainer2.appendChild(imageElement);
    }
}

//아이템 리스트 생성
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

//플레이어 정보 생성
const playerContainer = document.getElementById("playerInfo");
function setPlayerInfo(player) {
    playerContainer.innerHTML = "";

    let playerElement1 = document.createElement("div");
    playerElement1.className = "player-div";
    playerElement1.innerHTML = player.job + " " + player.hp + " / " + player.maxHp + "<br>"
    playerContainer.appendChild(playerElement1);
    let playerElement2 = document.createElement("div");
    playerElement2.className = "player-div";
    playerElement2.innerHTML = "Level "+player.level + " sp:" + player.sp + " dice:" + player.diceQuantity + "<br>"
    playerContainer.appendChild(playerElement2);
    
}

//적 정보 생성
const enemyContainer = document.getElementById("enemyInfo");
function setEnemyInfo(player) {
    enemyContainer.innerHTML = "";

    let playerElement1 = document.createElement("div");
    playerElement1.className = "enemy-div";
    playerElement1.innerHTML = player.name + " " + player.hp + " / " + player.maxHp + "<br>"
    enemyContainer.appendChild(playerElement1);
    let playerElement2 = document.createElement("div");
    playerElement2.className = "enemy-div";
    playerElement2.innerHTML = "Grade:"+player.grade + " dice:" + player.diceQuantity + "<br>"
    enemyContainer.appendChild(playerElement2);
}

//스크립트 출력
function setScript(script) {
    const scriptContainer = document.getElementById("script");
    scriptContainer.innerHTML = "<br>";

    for (let i = 0 ; i < script.length ; i ++) {
    let element = document.createElement("div");
        element.className = "script-div";
        element.innerHTML = script[i];
        scriptContainer.appendChild(element);
    }    

}

// 턴시작
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

            setPlayerInfo(data.player);
            setEnemyInfo(data.enemy);
            setDiceImage(diceList);
            setItemList(data.myTurn.item);

        })
        .catch(error => {
            console.error('Error:', error);
    });
};

function useItem() {
    const diceNum=selectDice();
    const itemNum=selectItem();
    console.log(diceNum);
    console.log(itemNum);

    if(diceNum==-1||itemNum==-1) return;

    console.log("둘다 입력받음");

	const postData = {
        idxDice: diceNum,
        idxItem: itemNum
    }

	fetch("./battleserv", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(postData)
    })
        .then(response => response.json())
        .then(data => {
            
            console.log(data);
            //스크립트 출력
            setScript(data.script)
            setPlayerInfo(data.player);
            setEnemyInfo(data.enemy);
            setDiceImage(data.myTurn.diceList);
            setItemList(data.myTurn.item);
                         
        })
        .catch(error => {
            console.error('Error:', error);
    });
}

let useItemButton = document.getElementById("useItem");

function myTurnEnd() {
    fetch("./battleserv", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            //스크립트 출력
            setScript(data.script)
            setPlayerInfo(data.player);
            setEnemyInfo(data.enemy);
            setDiceImage(data.myTurn.diceList);
            setItemList(data.myTurn.item);
						
            setDiceImage2(data.enemyTurn.diceList);
            
            console.log(data.myTurn.isTurn);
            if (data.myTurn.isTurn==true) {
                useItemButton.disabled = false;
                console.log("사용버튼 활성화");
            } else {
                useItemButton.disabled = true;
                console.log("사용버튼 비활성화");
            }
        })
        .catch(error => {
            console.error('Error:', error);
    });
};

function selectDice() {
    var container = document.getElementById("image-container");
        // 컨테이너 내의 모든 이미지 요소 가져오기
    var images = container.getElementsByTagName('img');

    let diceNum=-1;
    // 이미지를 찾는 반복문
    for (var i = 0; i < images.length; i++) {
        var currentImageSrc = images[i].src;
        if (imageRedPaths.includes(images[i].src)) {
            // 이미지가 배열에 있는 경우 해당 이미지에 대한 작업을 수행
            console.log('이미지를 찾았습니다:', i);
            // 추가 작업을 수행할 수 있습니다.
            diceNum=i;
        } else {
            console.log('이미지가 없습니다:', i);
        }
    }
    return diceNum;
}

function selectItem() {
    var container = document.getElementById("item-container");
        // 컨테이너 내의 모든 이미지 요소 가져오기
    var items = container.getElementsByTagName('div');

    let itemNum=-1;
    // 이미지를 찾는 반복문
    for (var i = 0; i < items.length; i++) {
        if (items[i].classList.contains("itemRed")){
            console.log('아이템을 찾았습니다:', i);
            itemNum=i;
        } else {
            console.log('아이템이 없습니다:', i);
        }
    }
    return itemNum;
}

