printInfo();
function printInfo() {
    fetch("./fieldserv", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            setPlayerInfo(data.player);
            setEnemyInfo(data.enemy);
            setItemList(data.player.inventory);
        })
        .catch(error => {
            console.error('Error:', error);
    });
    this.disabled = true;
};

const storeList = document.getElementById("store");
storeList.addEventListener("click", function(){
    fetch("./fieldserv", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);            
            setPlayerInfo(data.player);
            setEnemyInfo(data.enemy);
            setItemList(data.player.inventory);
            setStoreList(data.field.store.storeList)
        })
        .catch(error => {
            console.error('Error:', error);
    });
});

const storeContainer = document.getElementById("store-container");
function setStoreList(list) {
    storeContainer.innerHTML = "";
    for (let i = 0; i < list.length; i++) {
        let itemElement = document.createElement("div");
        itemElement.className = "store-div";
        itemElement.innerHTML = list[i].name+"<br><br>";
        itemElement.innerHTML +=list[i].description+"<br><br>";
        if (list[i].times>1) {
        	itemElement.innerHTML +="남은횟수 : "+list[i].times+"<br><br>";
        }
		if (list[i].count>0) {
        	itemElement.innerHTML +="카운트 : "+list[i].count+"<br><br>";
        }
		itemElement.classList.add("itemBasic");
        
        itemElement.addEventListener("click", function () {
        if (itemElement.classList.contains("itemBasic")) {
            console.log("검은색");
            resetStoreColor(list);
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
      storeContainer.appendChild(itemElement);
    }
}
function resetStoreColor(list) {
    for (let i = 0; i < list.length; i++) {
        document.getElementsByClassName("store-div")[i].classList.remove("itemRed");
        document.getElementsByClassName("store-div")[i].classList.add("itemBasic");
    }
}

//플레이어 정보 생성
const playerContainer = document.getElementById("playerInfo");
function setPlayerInfo(player) {
    playerContainer.innerHTML = "";
    let playerElement = document.createElement("div");
    playerElement.className = "player-div";
    playerElement.innerHTML = player.job + "  Level" + player.level + "<br>"
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
        playerElement.style.color = "red";
        playerContainer.appendChild(playerElement);
    }
    if (player.condition[1]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="냉기 : "+player.condition[1]+"<br>";
        playerElement.style.color = "blue";
        playerContainer.appendChild(playerElement);
    }
    if (player.condition[2]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="전기 : "+player.condition[2]+"<br>";
        playerElement.style.color = "yellow";
        playerContainer.appendChild(playerElement);
    }
    if (player.condition[3]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="독 : "+player.condition[3]+"<br>";
        playerElement.style.color = "purple";
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
        playerElement.style.color = "red";
        enemyContainer.appendChild(playerElement);
    }
    if (player.condition[1]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="냉기 : "+player.condition[1]+"<br>";
        playerElement.style.color = "blue";
        enemyContainer.appendChild(playerElement);
    }
    if (player.condition[2]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="전기 : "+player.condition[2]+"<br>";
        playerElement.style.color = "yellow";
        enemyContainer.appendChild(playerElement);
    }
    if (player.condition[3]>0) {
        playerElement = document.createElement("div");
        playerElement.innerHTML +="독 : "+player.condition[3]+"<br>";
        playerElement.style.color = "purple";
        enemyContainer.appendChild(playerElement);
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
        if (list[i].times>1) {
        	itemElement.innerHTML +="남은횟수 : "+list[i].times+"<br><br>";
        }
		if (list[i].count>0) {
        	itemElement.innerHTML +="카운트 : "+list[i].count+"<br><br>";
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


const forge = document.getElementById("forge");
forge.addEventListener("click", function(){
    let index = selectItem();
    fetch("./fieldserv", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);            
            setPlayerInfo(data.player);
            setEnemyInfo(data.enemy);
            setItemList(data.player.inventory);            
            forgeList(data.player.inventory);
            console.log(itemNum);
        })
        .catch(error => {
            console.error('Error:', error);
    });
});


function setForge(list, index) {
    const forgeContainer = document.getElementById("store-container");
    forgeContainer.innerHTML="";
    let forgeInfo = document.createElement("div");
    forgeInfo.innerHTML = list[index].enhName+"<br><br>";
    forgeInfo.innerHTML += list[index].enhDescription+"<br></br>";
    if (list[index].enhCount>0) {
        forgeInfo.innerHTML += "카운트 : "+list[index].enhCount;
    }
    console.log(index);
    forgeContainer.appendChild(forgeInfo);
}

//대장간 리스트로 생성
function forgeList(list, turn) {
    itemContainer.innerHTML = "";
    document.getElementById("store-container").innerHTML="";
    itemNum=-1;
    for (let i = 0; i < list.length; i++) {
        let itemElement = document.createElement("div");
        itemElement.className = "item-div";
        if (list[i].name=="빈슬롯"){
			itemElement.style.opacity = 0.3;
		}
        itemElement.innerHTML = list[i].name+"<br><br>";
        itemElement.innerHTML +=list[i].description+"<br><br>";
        if (list[i].times>1) {
        	itemElement.innerHTML +="남은횟수 : "+list[i].times+"<br><br>";
        }
		if (list[i].count>0) {
        	itemElement.innerHTML +="카운트 : "+list[i].count+"<br><br>";
        }
		itemElement.classList.add("itemBasic");
        
        itemElement.addEventListener("click", function () {
        if (itemElement.classList.contains("itemBasic")) {
            console.log("검은색");
            resetItemColor(list);
            itemElement.classList.remove("itemBasic");
            itemElement.classList.add("itemRed");
            selectItem();
            setForge(list, itemNum);
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

let itemNum;
function selectItem() {
    var container = document.getElementById("item-container");
    var items = container.getElementsByTagName('div');

    itemNum=-1;
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