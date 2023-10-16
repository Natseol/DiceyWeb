// 0부터 9까지의 Dice 이미지를 정의합니다.
const imagePaths = [
    "image/book.png",
    "image/dice1.png",
    "image/dice2.png",
    "image/dice3.png",
    "image/dice4.png",
    "image/dice5.png",
    "image/dice6.png",
    "image/dice7.png",
    "image/dice8.png",
    "image/dice9.png",
    "image/dice10.png"
];

const imageRedPaths = [
    "image/bookred.png",
    "image/dicered1.png",
    "image/dicered2.png",
    "image/dicered3.png",
    "image/dicered4.png",
    "image/dicered5.png",
    "image/dicered6.png",
    "image/dicered7.png",
    "image/dicered8.png",
    "image/dicered9.png",
    "image/dicered10.png"
];

let diceDragNum = -1;
//주사위 그림 생성
const imageContainer = document.getElementById("image-container");
function setDiceImage(list) {
    imageContainer.innerHTML = "";
    for (let i = 0; i < list.length; i++) {

        let imageElement = document.createElement("img");
        imageElement.className = "dice-image";
        imageElement.src = imagePaths[list[i]];
        imageElement.draggable=true;        
        imageElement.ondragstart=function(event) {
			diceDragNum = i;		
		}
        
        // imageElement.addEventListener("click", function() {
        //     console.log(imageElement.src);
        //     console.log(imagePaths[list[i]]);
        //     if (imageElement.src.includes(imagePaths[list[i]])) {
        //         console.log("검은색");
        //         resetDiceImage(list);
        //         imageElement.src = imageRedPaths[list[i]];
                
        //     } else if (imageElement.src.includes(imageRedPaths[list[i]])) {
        //         console.log("빨강");
        //         imageElement.src = imagePaths[list[i]];
        //     } else {
        //         console.log("안됨");
        //     }
        // });
        // dragdropItem(imageElement);
        ronundOver(imageElement);      
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

let itemDragNum = -1;
//아이템 리스트 생성
const itemContainer = document.getElementById("item-container");
function setItemList(list, turn) {
    itemContainer.innerHTML = "";
    for (let i = 0; i < list.length; i++) {
        let itemElement = document.createElement("div");
        itemElement.className = "item-div";
        itemElement.classList.add("rounded-3");
        itemElement.classList.add("bg-light");
        itemElement.classList.add("bg-gradient");
        if (list[i].name=="빈슬롯"){
			itemElement.style.opacity = 0.3;
		}
        itemElement.innerHTML = "<span style='font-weight:bold'>"+list[i].name+"</span><br><br>";
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
		
		itemElement.addEventListener("dragenter", ronundOver(itemElement));

        itemElement.addEventListener("dragover", (e) => {
            e.preventDefault();
            itemElement.style.boxShadow = '0 0 6px 1px black';
        })
        itemElement.addEventListener('dragleave', () => {
            itemElement.style.boxShadow = 'none';
        });
        itemElement.addEventListener("drop", (e) => {
            e.preventDefault();
            itemDragNum = i;
            console.log(diceDragNum+" "+itemDragNum);
            useDragItem(diceDragNum, itemDragNum)
        });

        // itemElement.addEventListener("click", function () {
        //     if (itemElement.classList.contains("itemBasic")) {
        //         console.log("검은색");
        //         resetItemColor(list);
        //         itemElement.classList.remove("itemBasic");
        //         itemElement.classList.add("itemRed");
        //     } else if (itemElement.classList.contains("itemRed")) {
        //         console.log("빨강");
        //         itemElement.classList.remove("itemRed");
        //         itemElement.classList.add("itemBasic");
        //     } else {
        //         console.log("안됨");
        //     }
        // });
        // ronundOver(itemElement);
        // itemElement.appendChild(imageEdge());
        itemContainer.appendChild(itemElement);
    }
}

function useDragItem(num1, num2) {
	const postData = {
        idxDice: num1,
        idxItem: num2,
        isUseSkill: "false"
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
            checkSp(data.player);
            gameover(data.player, data.enemy)
        })
        .catch(error => {
            console.error('Error:', error);
    });
}

//마우스오버시 테두리
function ronundOver(itemElement) {
    const span = itemElement.querySelector("span");
    itemElement.onmouseover=(e)=>{
        e.target.style = "box-shadow: 0 0 6px 1px black;"
        if (span!=null) {
            span.style = "border: 0px solid #000; font-weight: bold;;"
        }
        console.log("마우스오버");
    }

    itemElement.onmouseleave=(e)=>{
        e.target.style = "box-shadow:;"
        if (span!=null) {
            span.style = "border: 0px solid #000; font-weight: bold;;"
        }
        if (e.target.innerHTML.includes("빈슬롯")) {
            e.target.style.opacity = 0.5;
        }
        console.log("마우스리브");
        console.log(e);
    }
}

//카드 이미지 생성
function imageEdge() {
    let imageEdge = document.createElement("img");
    imageEdge.src = "image/edge1.png";
    imageEdge.className = "image-edge";
    return imageEdge;
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
    playerElement1.innerHTML = player.job + "  Level" + player.level + "<br>"

    playerContainer.appendChild(playerElement1);
        let playerHp = document.createElement("div");
        playerHp.className = "hp";
        let playerInnerHp = document.createElement("div");
        playerInnerHp.className = "innerHp";
        playerHp.appendChild(playerInnerHp);
    playerContainer.appendChild(playerHp);

    let playerElement = document.createElement("div");
    playerElement.className = "player-div";
    playerElement.innerHTML += "HP: "+player.hp;
    if (player.def>0) { 
		playerElement.innerHTML += " <span style='color:gold; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;'>("+player.def+")</span>";
	}
    playerElement.innerHTML += " / " + player.maxHp + "<br>"
    playerElement.innerHTML +=" Dice:" + player.diceQuantity +  " SP:" + player.sp + "<br>"
    playerContainer.appendChild(playerElement);
    if (player.condition[0]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="화염 : "+player.condition[0]+"<br>";
        playerElement.style.color = "rgb(255, 71, 71)";
        playerElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        playerContainer.appendChild(playerElement);
    }
    if (player.condition[1]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="냉기 : "+player.condition[1]+"<br>";
        playerElement.style.color = "skyblue";
        playerElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        playerContainer.appendChild(playerElement);
    }
    if (player.condition[2]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="전기 : "+player.condition[2]+"<br>";
        playerElement.style.color = "yellow";
        playerElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        playerContainer.appendChild(playerElement);
    }
    if (player.condition[3]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="독 : "+player.condition[3]+"<br>";
        playerElement.style.color = "rgb(204, 120, 253)";
        playerElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        playerContainer.appendChild(playerElement);
    }
}

//적 정보 생성
const enemyContainer = document.getElementById("enemyInfo");
function setEnemyInfo(player) {
    enemyContainer.innerHTML = "";
    let playerElement = document.createElement("div");
    playerElement.className = "enemy-div";
    playerElement.innerHTML = player.name + "  (" + player.grade + ") <br>"
    playerElement.innerHTML += "HP: "+player.hp;
    if (player.def>0) { 
		playerElement.innerHTML += " <span style='color:gold; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;'>("+player.def+")</span>";
	}
    playerElement.innerHTML += " / " + player.maxHp + "<br>"
    playerElement.innerHTML +=" Dice:" + player.diceQuantity + "<br>"
    enemyContainer.appendChild(playerElement);
    if (player.condition[0]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="화염 : "+player.condition[0]+"<br>";
        playerElement.style.color = "rgb(255, 71, 71)";
        playerElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        enemyContainer.appendChild(playerElement);
    }
    if (player.condition[1]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="냉기 : "+player.condition[1]+"<br>";
        playerElement.style.color = "skyblue";
        playerElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        enemyContainer.appendChild(playerElement);
    }
    if (player.condition[2]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="전기 : "+player.condition[2]+"<br>";
        playerElement.style.color = "yellow";
        playerElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        enemyContainer.appendChild(playerElement);
    }
    if (player.condition[3]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="독 : "+player.condition[3]+"<br>";
        playerElement.style.color = "rgb(204, 120, 253)";
        playerElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        enemyContainer.appendChild(playerElement);
    }
}

//스크립트 출력
function setScript(script) {
    const scriptContainer = document.getElementById("script");
    scriptContainer.innerHTML = "<br>";

    for (let i = 0 ; i < script.length ; i ++) {
        let element = document.createElement("span");
        element.className = "script-div";
        element.innerHTML = script[i];
        if (script[i].includes("불태")||script[i].includes("건들")) {
            element.style.backgroundColor="rgb(255, 71, 71)"
        } else if (script[i].includes("얼립")||script[i].includes("얼어")) {
            element.style.backgroundColor="skyblue"
        } else if (script[i].includes("감전")||script[i].includes("충격")) {
            element.style.backgroundColor="yellow"
        }else if (script[i].includes("중독")) {
            element.style.backgroundColor="rgb(204, 120, 253)"
        }else if(script[i].includes("DIED")){
            element.style.color = "red";
            element.style.fontSize = "25px";
            element.style.fontWeight = "bold";
        }else if(script[i].includes("피해")){
            element.style.fontWeight = "bold";
        } else {
            console.log("미포함")
        }
        scriptContainer.appendChild(element);        
    }    
}

function selectDice() {
    let container = document.getElementById("image-container");
    let images = container.getElementsByTagName('img');

    let diceNum=-1;
    for (let i = 0; i < images.length; i++) {
        let imagesSrc=images[i].src;
        let newSrc=imagesSrc.slice(imagesSrc.indexOf('DiceyWeb')+9);//로컬 주소 바뀔 시 오류 가능성 있음
        if (imageRedPaths.includes(newSrc)) {
            console.log('이미지를 찾았습니다:', i);
            diceNum=i;
        } else {
            console.log('이미지가 없습니다:', newSrc);
            console.log('이미지가 없습니다:', i);
        }
    }
    return diceNum;
}

function selectItem() {
    let container = document.getElementById("item-container");
    let items = container.getElementsByTagName('div');

    let itemNum=-1;
    for (let i = 0; i < items.length; i++) {
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
    const next = document.getElementById("nextField");
    const nextButton = document.createElement('button');
    nextButton.className="btn btn-success btn-lg";
    nextButton.type="button";
    nextButton.innerHTML="다음으로";
    nextButton.addEventListener("click", function() {

        const postData = {
        endStage: "true",
        }

        fetch("./battleserv", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        })   
            .catch(error => {
                console.error('Error:', error);
        });
        location.href="field.html";
    });
    next.appendChild(nextButton);
}

function gameover(player, enemy) {
    const script = document.getElementById("script");
    if (player.hp<1) {
        disabledAllButton();
        disableDrag(false);        
        return;
    }
    if (enemy.hp<1) {
		disableDrag(false);
        disabledAllButton();
        createNextButton();
        return;
    }
}

function createSkillButton() {
    const skill = document.getElementById("skill");
    const skillButton = document.createElement('button');
    skillButton.className="btn btn-warning";
    skillButton.type="button";
    skillButton.setAttribute("id", "skillButton");
    skillButton.innerHTML="스킬발동";
    skillButton.addEventListener("click", function() {
        let isUseSkill = "true";
        const postData = {
            idxDice: "0",
            idxItem: "0",
            isUseSkill: isUseSkill
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
                document.getElementById("script").innerHTML=data.skillScript;
                setPlayerInfo(data.player);
                setEnemyInfo(data.enemy);
                setDiceImage(data.myTurn.diceList);
                setItemList(data.myTurn.item, data.myTurn);
                checkSp(data.player);
                gameover(data.player, data.enemy);
                if (data.player.job=="마법사") useMagic();
            })
            .catch(error => {
                console.error('Error:', error);
        });
    });
    skill.appendChild(skillButton);
}

function useMagic() {
    const script = document.getElementById("script");
    const form = document.createElement("form");
    const input = document.createElement("input");
    input.style.margin="10px 10px";
    input.style.width="50px";
    input.style.height="38px";
    // 전송 버튼을 추가합니다.
    let submitButton = document.createElement('button');
    submitButton.textContent = '생성';
    submitButton.className = "btn btn-warning"
    submitButton.style.width="60px"
    submitButton.style.margin="0px 10px "
    
    submitButton.addEventListener('click', function() {
        const postData = {
            magicDice: input.value
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
                printDice();
                document.getElementById("script").innerHTML=data.skillScript;
            })
            .catch(error => {
                console.error('Error:', error);
        }); 
    
    });

    script.appendChild(input);
    script.appendChild(submitButton);
    script.appendChild(form);
}

function checkSp(player) {
    if(document.getElementById("skillButton")){
        document.getElementById("skill").removeChild(document.getElementById("skillButton"));
    }
    if (player.sp>10+player.level) {
        createSkillButton();
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

            setPlayerInfo(data.player);
            setEnemyInfo(data.enemy);
            setDiceImage(data.myTurn.diceList);
            setItemList(data.myTurn.item, data.myTurn);
            checkSp(data.player);
            reverseTurnImage(data.myTurn.isTurn);
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
        idxItem: itemNum,
        isUseSkill: "false"
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
            checkSp(data.player);
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
            checkSp(data.player);
            setDiceImage2(data.enemyTurn.diceList);
            reverseTurnImage(data.myTurn.isTurn);
            disableDrag(data.myTurn.isTurn);
            console.log(data.myTurn.isTurn);
            if (data.myTurn.isTurn==true) {
                //useItemButton.disabled = false;
                setItemList(data.myTurn.item, data.myTurn);                 
                console.log("사용버튼 활성화");
                document.getElementById("endTurn").textContent = "턴종료";
            } else {
                //useItemButton.disabled = true;
                console.log("사용버튼 비활성화");
				document.getElementById("endTurn").textContent = "다음";
            }
            gameover(data.player, data.enemy)
        })
        .catch(error => {
            console.error('Error:', error);
    });
};
function reverseTurnImage(isCheck) {	
	const playerDiv = document.getElementById("player-info-div");
	const enemyDiv = document.getElementById("enemy-info-div");
	if (isCheck) {
	playerDiv.style="box-shadow: 0 0 20px 0px darkred";
	enemyDiv.style="box-shadow:;";		
	} else {
	playerDiv.style="box-shadow:;";
	enemyDiv.style="box-shadow: 0 0 20px 0px darkred";		
	}
}

function disableDrag(isCheck) {	
    const diceImages = document.getElementsByClassName("dice-image");
    for (let i = 0; i < diceImages.length; i++) {
        diceImages[i].draggable = isCheck;
    }
}


function modalItemList(list) {
	let modalItem = document.getElementById("modal-body")
    for (let i = 0; i < list.length; i++) {
	    let modalItemElement = document.createElement("div");
	    modalItemElement.className = "modal-div";
	    modalItemElement.classList.add("rounded-3");
	    modalItemElement.classList.add("bg-light");
	    modalItemElement.classList.add("bg-gradient");        
	    modalItemElement.innerHTML = "<span style='font-weight:bold'>"+list[i].name+"</span><br><br>";
	    modalItemElement.innerHTML +=list[i].description+"<br>";
	    if (list[i].times>1) {
	    	modalItemElement.innerHTML +="<br>남은횟수 : "+list[i].times+"<br>";
	    }
		if (list[i].count>0) {
	    	modalItemElement.innerHTML +="<br>카운트 : "+list[i].count+"<br>";
	    }
     	modalItem.appendChild(modalItemElement);
    }
}

//모달 버튼
const modalButton = document.getElementById("modal-button");
modalButton.addEventListener("click", function() {
    const modalName = document.getElementById("exampleModalLabel");

    const modalItem = document.getElementById("modal-body")

    fetch("./battleserv", {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log(data.enemy.name);
        
        modalName.innerHTML = data.enemy.name + " (" + data.enemy.grade+")";
        modalName.style.fontWeight = "bold";        
        modalItem.innerHTML = data.enemy.description+"<br><br>";
        modalItem.innerHTML += "주사위 : "+data.enemy.diceQuantity+"개<br><br>";
        modalItem.innerHTML += "<인벤토리>";
        modalItemList(data.enemy.inventory);
    })
    .catch(error => {
        console.error('Error:', error);
    });
})