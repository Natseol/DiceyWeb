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
function setItemList(list, turn) {
    itemContainer.innerHTML = "";
    for (let i = 0; i < list.length; i++) {
        let itemElement = document.createElement("div");
        itemElement.className = "item-div";
        if (list[i].name=="빈슬롯"){
			itemElement.style.opacity = 0.3;
		}
        itemElement.innerHTML = list[i].name+"<br><br>";
        itemElement.innerHTML +=list[i].description+"<br><br>";
        if (turn.timesState[i]>1) {
        	itemElement.innerHTML +="남은횟수 : "+turn.timesState[i]+"<br><br>";
        }
		if (turn.countState[i]>0) {
        	itemElement.innerHTML +="카운트 : "+turn.countState[i]+"<br><br>";
        }
        if (turn.needDiceState[i]>0) {
        	itemElement.innerHTML +="누적 : "+turn.needDiceState[i]+"<br><br>";
        }
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
    playerElement1.innerHTML = player.job + " " + player.hp
    if (player.def>0) { 
		playerElement1.innerHTML += " <span style='color:gold; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;'>("+player.def+")</span>";
	}
    playerElement1.innerHTML += " / " + player.maxHp + "<br>"
    playerContainer.appendChild(playerElement1);
    let playerElement2 = document.createElement("div");
    playerElement2.className = "player-div";
    playerElement2.innerHTML = "Level "+player.level + " sp:" + player.sp + " dice:" + player.diceQuantity + "<br>"
    playerContainer.appendChild(playerElement2);
    if (player.condition[0]>0) {
        let playerElement3 = document.createElement("div");
        playerElement3.innerHTML +="화염 : "+player.condition[0]+"<br>";
        playerElement3.style.color = "red";
        playerContainer.appendChild(playerElement3);
    }
    if (player.condition[1]>0) {
        let playerElement3 = document.createElement("div");
        playerElement3.innerHTML +="냉기 : "+player.condition[1]+"<br>";
        playerElement3.style.color = "blue";
        playerContainer.appendChild(playerElement3);
    }
    if (player.condition[2]>0) {
        let playerElement3 = document.createElement("div");
        playerElement3.innerHTML +="전기 : "+player.condition[2]+"<br>";
        playerElement3.style.color = "yellow";
        playerContainer.appendChild(playerElement3);
    }
    if (player.condition[3]>0) {
        let playerElement3 = document.createElement("div");
        playerElement3.innerHTML +="독 : "+player.condition[3]+"<br>";
        playerElement3.style.color = "purple";
        playerContainer.appendChild(playerElement3);
    }
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
    if (player.condition[0]>0) {
        let playerElement3 = document.createElement("div");
        playerElement3.innerHTML +="화염 : "+player.condition[0]+"<br>";
        playerElement3.style.color = "red";
        enemyContainer.appendChild(playerElement3);
    }
    if (player.condition[1]>0) {
        let playerElement3 = document.createElement("div");
        playerElement3.innerHTML +="냉기 : "+player.condition[1]+"<br>";
        playerElement3.style.color = "blue";
        enemyContainer.appendChild(playerElement3);
    }
    if (player.condition[2]>0) {
        let playerElement3 = document.createElement("div");
        playerElement3.innerHTML +="전기 : "+player.condition[2]+"<br>";
        playerElement3.style.color = "yellow";
        enemyContainer.appendChild(playerElement3);
    }
    if (player.condition[3]>0) {
        let playerElement3 = document.createElement("div");
        playerElement3.innerHTML +="독 : "+player.condition[3]+"<br>";
        playerElement3.style.color = "purple";
        enemyContainer.appendChild(playerElement3);
    }
}

//스크립트 출력
function setScript(script) {
    const scriptContainer = document.getElementById("script");
    scriptContainer.innerHTML = "<br>";

    for (let i = 0 ; i < script.length ; i ++) {
        let element = document.createElement("div");
        element.className = "script-div";
        element.innerHTML = script[i];
        if (script[i].includes("불태")) {
            element.style.color="red"
        } else if (script[i].includes("얼립")) {
            element.style.color="blue"
        } else if (script[i].includes("감전")) {
            element.style.color="yellow"
        }else if (script[i].includes("중독")) {
            element.style.color="purple"
        }else {
            console.log("미포함")
        }
        scriptContainer.appendChild(element);
    }    
}

function selectDice() {
    var container = document.getElementById("image-container");
    var images = container.getElementsByTagName('img');

    let diceNum=-1;
    for (var i = 0; i < images.length; i++) {
        var currentImageSrc = images[i].src;
        if (imageRedPaths.includes(images[i].src)) {
            console.log('이미지를 찾았습니다:', i);
            diceNum=i;
        } else {
            console.log('이미지가 없습니다:', i);
        }
    }
    return diceNum;
}

function selectItem() {
    var container = document.getElementById("item-container");
    var items = container.getElementsByTagName('div');

    let itemNum=-1;
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

function disabledAllButton() {
    const buttons = document.querySelectorAll("button");
    buttons.forEach(button => {
    button.disabled = true;
    });
}

function createNextButton() {
    const next = document.getElementById("nextStage");
    const nextButton = document.createElement('button');
    nextButton.className="btn btn-success";
    nextButton.type="button";
    nextButton.innerHTML="다음으로";
    nextButton.addEventListener("click", nextStage());
    next.appendChild(nextButton);
}

function gameover(player, enemy) {
    const script = document.getElementById("script");
    if (player.hp<1) {
        disabledAllButton();
        script.innerHTML = "YOU DIED";
        script.style.color = "red";
        script.style.fontSize = "25px";
        script.style.fontWeight = "bold";
        return;
    }
    if (enemy.hp<1) {
        disabledAllButton();
        createNextButton();
        script.innerHTML = "승리";
        return;
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

            console.log(data.myTurn.diceList);
            console.log(data.myTurn.item);

            setPlayerInfo(data.player);
            setEnemyInfo(data.enemy);
            setDiceImage(data.myTurn.diceList);
            setItemList(data.myTurn.item, data.myTurn);
            gameover(data.player, data.enemy);
        })
        .catch(error => {
            console.error('Error:', error);
    });
    this.disabled = true;
};
printDice();

// 사용버튼
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
            setItemList(data.myTurn.item, data.myTurn);
            gameover(data.player, data.enemy)
        })
        .catch(error => {
            console.error('Error:', error);
    });
}

let useItemButton = document.getElementById("useItem");
//턴종료 버튼
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
            setItemList(data.enemyTurn.item, data.enemyTurn);
						
            setDiceImage2(data.enemyTurn.diceList);
            
            console.log(data.myTurn.isTurn);
            if (data.myTurn.isTurn==true) {
                useItemButton.disabled = false;
                setItemList(data.myTurn.item, data.myTurn); 
                console.log("사용버튼 활성화");
            } else {
                useItemButton.disabled = true;
                console.log("사용버튼 비활성화");
            }
            gameover(data.player, data.enemy)
        })
        .catch(error => {
            console.error('Error:', error);
    });
};

//턴종료 버튼
function nextStage() {    

    fetch("./battleserv", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .catch(error => {
            console.error('Error:', error);
    });
};

