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

function stageNum(num) {
    let text;
    if (num>=0&&num<4) {
        text = "1 - "+(num+1);
    } else if (num>=4&&num<7) {
        text = "2 - "+(num-3);
    } else if (num>=7&&num<10) {
        text = "3 - "+(num-6);
    } else if (num>=10&&num<14) {
        text = "4 - "+(num-9);
    } else if (num>=14&&num<17) {
        text = "5 - "+(num-13);
    } else if (num==17) {
        text = "BOSS";
    }
    return text;
}
function setStageNum() {
    const stageText = document.getElementById("stageText");
    fetch("./battleserv", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            stageText.innerHTML="STAGE<BR>"+stageNum(data.enemyNum);
        })
        .catch(error => {
            console.error('Error:', error);
    });
    this.disabled = true;
}

window.addEventListener('load', function () {
    setStageNum();
    let loadingOverlay = document.getElementById('loading-overlay');
    let centeredContent = document.querySelector('.centered-content');

    // 3초 후 로딩 오버레이 사라지게 함
    setTimeout(function () {
        loadingOverlay.style.opacity = 0;
        centeredContent.style.display = 'none'; // 로딩 중 텍스트 숨기기
    }, 2000); // 3초 후 오버레이를 투명하게 함
});

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
        itemElement.classList.add("text-white");
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
            itemElement.style.boxShadow = '0 0 10px 7px black';
            itemElement.style.border = '2px solid white';
        })
        itemElement.addEventListener('dragleave', () => {
            itemElement.style.border = '4px outset black';
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
        e.target.style = "box-shadow: 0 0 10px 7px black;"
        if (span!=null) {
            span.style = "border: 0px solid #000; font-weight: bold;;"
        }
    }

    itemElement.onmouseleave=(e)=>{
        e.target.style = "box-shadow:;"
        if (span!=null) {
            span.style = "border: 0px solid #000; font-weight: bold;;"
        }
        if (e.target.innerHTML.includes("빈슬롯")) {
            e.target.style.opacity = 0.5;
        }
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

function changeHp(player) {
    let playerHp = document.createElement("div");
    playerHp.className = "hp";
    let playerInnerHp = document.createElement("div");
    playerInnerHp.className = "innerHp";
    let hpP = 100-(player.hp/player.maxHp*100);
    playerInnerHp.style.transform = 'translateX(-'+hpP+'%)';
    if (hpP>70) {
        playerInnerHp.style.backgroundColor ='red';
    } else if (hpP>40) {
        playerInnerHp.style.backgroundColor ='rgb(229,213,15)';
    } 
    if (player.def>0) {
        playerInnerHp.style.backgroundColor ='white';
    }
    
    playerHp.appendChild(playerInnerHp);    
    return playerHp;
}
function drawIcon(player) {
    let playerIcon = document.createElement("img");
    playerIcon.className = "player-icon";
    if (player.job=="전사") {
        playerIcon.src="image/warrior.png";
    } else if (player.job=="도적") {
        playerIcon.src="image/rogue.png";
    } else if (player.job=="마법사") {
        playerIcon.src="image/magician.png";
    } else if (player.job=="궁수") {
        playerIcon.src="image/archer.png";
    } else if (player.job=="기사") {
        playerIcon.src="image/knight.png";
    }
    return playerIcon;
}

function hundredSp(player) {
    let sp = Math.ceil((player.sp/(10+player.level))*100)
    sp>=100 ? sp = 100 : sp;
    return sp;
}
//플레이어 정보 생성
const playerContainer = document.getElementById("playerInfo");
function setPlayerInfo(player) {
    playerContainer.innerHTML = "";
    playerContainer.appendChild(drawIcon(player));
    let playerElement1 = document.createElement("div");
    playerElement1.className = "player-div";
    playerElement1.innerHTML = player.job + "  Level " + player.level + "<br>"
    playerContainer.appendChild(playerElement1);

    playerContainer.appendChild(changeHp(player));

    let playerElement = document.createElement("div");
    playerElement.className = "player-div";
    playerElement.innerHTML += "HP: "+player.hp;
    if (player.def>0) { 
		playerElement.innerHTML += " <span style='color:gold; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;'>("+player.def+")</span>";
	}
    playerElement.innerHTML += " / " + player.maxHp + "<br>"
    playerElement.innerHTML +=" Dice:" + player.diceQuantity +  " SP:" + hundredSp(player) + "<br>"
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

function drawEnemyIcon(enemy) {
    let enemyIcon = document.createElement("img");
    enemyIcon.className = "player-icon";
    if (enemy.grade=="일반") {
        enemyIcon.src="image/gradeN.png";
    } else if (enemy.grade=="어려움") {
        enemyIcon.src="image/gradeH.png";
    } else if (enemy.grade=="보스") {
        enemyIcon.src="image/gradeB.png";
    } 
    return enemyIcon;
}

//적 정보 생성
const enemyContainer = document.getElementById("enemyInfo");
function setEnemyInfo(enemy) {
    enemyContainer.innerHTML = "";
    enemyContainer.appendChild(drawEnemyIcon(enemy));
    let enemyElement1 = document.createElement("div");
    enemyElement1.className = "enemy-div";
    enemyElement1.innerHTML = enemy.name + "  (" + enemy.grade + ") <br>"
    enemyContainer.appendChild(enemyElement1);
    enemyContainer.appendChild(changeHp(enemy));
    let enemyElement = document.createElement("div");
    enemyElement.innerHTML = "HP: "+enemy.hp;
    if (enemy.def>0) { 
		enemyElement.innerHTML += " <span style='color:gold; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;'>("+enemy.def+")</span>";
	}
    enemyElement.innerHTML += " / " + enemy.maxHp + "<br>"
    enemyElement.innerHTML +=" Dice:" + enemy.diceQuantity + "<br>"
    enemyContainer.appendChild(enemyElement);
    if (enemy.condition[0]>0) {
        enemyElement = document.createElement("div");
        enemyElement.innerHTML +="화염 : "+enemy.condition[0]+"<br>";
        enemyElement.style.color = "rgb(255, 71, 71)";
        enemyElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        enemyContainer.appendChild(enemyElement);
    }
    if (enemy.condition[1]>0) {
        enemyElement = document.createElement("div");
        enemyElement.innerHTML +="냉기 : "+enemy.condition[1]+"<br>";
        enemyElement.style.color = "skyblue";
        enemyElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        enemyContainer.appendChild(enemyElement);
    }
    if (enemy.condition[2]>0) {
        enemyElement = document.createElement("div");
        enemyElement.innerHTML +="전기 : "+enemy.condition[2]+"<br>";
        enemyElement.style.color = "yellow";
        enemyElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        enemyContainer.appendChild(enemyElement);
    }
    if (enemy.condition[3]>0) {
        enemyElement = document.createElement("div");
        enemyElement.innerHTML +="독 : "+enemy.condition[3]+"<br>";
        enemyElement.style.color = "rgb(204, 120, 253)";
        enemyElement.style.textShadow = "1px 1px 1px rgba(0, 0, 0, 1)";
        enemyContainer.appendChild(enemyElement);
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

function gameoverDisplay() {
	const loadingOverlay = document.getElementById('loading-overlay');
	const stageText = document.getElementById("stageText");
	let centeredContent = document.querySelector('.centered-content');
    centeredContent.style.display = "block";
    stageText.innerHTML = "패배<br><br>";
    const reButton = document.createElement("a");
    reButton.innerHTML="<다시하기>";
    reButton.href = "../DiceyWeb/";
    reButton.style.textDecoration = "none";
    reButton.style.fontSize = "x-large";
    reButton.style.color = "red";
    stageText.appendChild(reButton);
    loadingOverlay.style = "background-image : linear-gradient( rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.7) ), url(./image/gameover.jpg)";
    loadingOverlay.style.opacity = 1;
    loadingOverlay.style.pointerEvents = "auto";
}

function endGameDisplay() {
	const loadingOverlay = document.getElementById('loading-overlay');
	const stageText = document.getElementById("stageText");
	let centeredContent = document.querySelector('.centered-content');
    centeredContent.style.display = "block";
    stageText.innerHTML = "축하합니다<br><br>모든 적을 쓰러트렸습니다";
    stageText.style.color = "black";
    loadingOverlay.style = "background-image : linear-gradient( rgba(255, 255, 255, 0.1), rgba(255, 255, 255, 0.2) ), url(./image/back4.jpg)";
    loadingOverlay.style.opacity = 1;
}

function gameover(player, enemy) {

    const script = document.getElementById("script");
    
    if (player.hp<1) {
        disabledAllButton();
        disableDrag(false);
        gameoverDisplay();   
        return;
    }
    if (enemy.hp<1) {
        if(enemy.name=="부활한 뱀파이어") {
            endGameDisplay();
            disableDrag(false);
            disabledAllButton();    
        } else {
            disableDrag(false);
            disabledAllButton();
            createNextButton();
        }		
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
	playerDiv.style="box-shadow: 0 0 20px 0px red";
	playerDiv.style.border = "7px dashed red";
	enemyDiv.style="box-shadow:;";
	enemyDiv.style.border = "";
	} else {
	playerDiv.style="box-shadow:;";
	playerDiv.style.border = "";
	enemyDiv.style="box-shadow: 0 0 20px 0px red";
	enemyDiv.style.border = "7px dashed red";		
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