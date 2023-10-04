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
            if(document.getElementById("storeButton")){
                document.getElementById("store-button").removeChild(document.getElementById("storeButton"));
            }
            if (data.field.storeCount>0) {
                setStoreList(data.field.store.storeList)
                createStoreButton()
            } else {
                storeContainer.innerHTML="모든 횟수를 소진했습니다"
            }
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
        itemElement.classList.add("rounded-3");
        itemElement.classList.add("bg-info");
        itemElement.classList.add("bg-gradient");
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

function createStoreButton() {
    const store = document.getElementById("store-button");
    const skillButton = document.createElement('button');
    skillButton.className="btn btn-warning";
    skillButton.type="button";
    skillButton.setAttribute("id", "storeButton");
    skillButton.innerHTML="교환";
    skillButton.addEventListener("click", function() {
        const itemNum=selectItem();
        const storeNum=selectStore();
        console.log(itemNum);
        console.log(storeNum);

        if(storeNum==-1||itemNum==-1) return;
        console.log("둘다 입력받음");


        const postData = {
            store: "true",
            forge: "false",
            well: "false",
            idxItem: itemNum,
            idxStore: storeNum
        }
    
        fetch("./fieldserv", {
            
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
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
        if(document.getElementById("storeButton")){
            document.getElementById("store-button").removeChild(document.getElementById("storeButton"));
        }
        if(document.getElementById("store-div")){
            document.getElementById("store-container").removeChild(document.getElementById("store-div"));
        }
    });
    store.appendChild(skillButton);
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
    playerElement.innerHTML += "<span style='font-weight: bold'>다음 상대</span><br><br>"
    playerElement.innerHTML += player.name + "  (" + player.grade + ") <br>"
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
    const enemyDes = document.getElementById("enemyDescription");
    enemyDes.innerHTML = "<span style='font-weight: bold'>설명</span><br><br>"+player.description;
}

//아이템 리스트 생성
const itemContainer = document.getElementById("item-container");
function setItemList(list) {
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

            if (data.field.forgeCount>0) {
                forgeList(data.player.inventory);
                createForgeButton();
            } else {
                storeContainer.innerHTML="모든 횟수를 소진했습니다"
            }
        })
        .catch(error => {
            console.error('Error:', error);
    });
    if(document.getElementById("storeButton")){
        document.getElementById("store-button").removeChild(document.getElementById("storeButton"));
    }
});


function setForge(list, index) {
    const forgeContainer = document.getElementById("store-container");
    forgeContainer.innerHTML="";
    let forgeInfo = document.createElement("div");
    forgeInfo.className = "forge-div";
    forgeInfo.classList.add("rounded-3");
    forgeInfo.classList.add("bg-secondary");
    forgeInfo.classList.add("bg-gradient"); 
    forgeInfo.style.color="white";
    forgeInfo.innerHTML = list[index].enhName+"<br><br>";
    forgeInfo.innerHTML += list[index].enhDescription+"<br></br>";
    if (list[index].enhCount>0) {
        forgeInfo.innerHTML += "카운트 : "+list[index].enhCount+"<br>";
    } else {
        forgeInfo.innerHTML += "<br><br><br>"
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
        itemElement.classList.add("rounded-3");
        itemElement.classList.add("bg-light");
        itemElement.classList.add("bg-gradient");        
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

function createForgeButton() {
    const store = document.getElementById("store-button");
    const skillButton = document.createElement('button');
    skillButton.className="btn btn-warning";
    skillButton.type="button";
    skillButton.setAttribute("id", "storeButton");
    skillButton.innerHTML="강화";
    skillButton.addEventListener("click", function() {
        const itemNum=selectItem();
        console.log(itemNum);        

        if(itemNum==-1) return;
        console.log("입력받음");


        const postData = {
            store: "false",
            forge: "true",
            well: "false",
            idxItem: itemNum,
            idxStore: "-1"
        }
    
        fetch("./fieldserv", {
            
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
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
        if(document.getElementById("storeButton")){
            document.getElementById("store-button").removeChild(document.getElementById("storeButton"));
        }
        if(document.getElementById("store-div")){
            document.getElementById("store-container").removeChild(document.getElementById("store-div"));
        }
    });
    store.appendChild(skillButton);
}

const well = document.getElementById("well");
well.addEventListener("click", function(){
    const storeContainer = document.getElementById("store-container");
    storeContainer.innerHTML="";
    let itemElement = document.createElement("div");
    itemElement.className = "well-div";
    itemElement.setAttribute("id", "wellScript");
    itemElement.classList.add("rounded-3");
    itemElement.classList.add("bg-success");
    itemElement.classList.add("bg-gradient"); 
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
            if(document.getElementById("storeButton")){
                document.getElementById("store-button").removeChild(document.getElementById("storeButton"));
            }
            if (data.field.healCount>0) {
                itemElement.innerHTML="체력을 ["+(data.player.level+4)+"] 회복합니다<br><br>"
                itemElement.style.color="white";
                createWellButton()
            } else {
                itemElement.innerHTML="모든 횟수를 소진했습니다"
            }
        })
        .catch(error => {
            console.error('Error:', error);
    });
    storeContainer.appendChild(itemElement);
});
function createWellButton() {
    const store = document.getElementById("store-button");
    const skillButton = document.createElement('button');
    skillButton.className="btn btn-warning";
    skillButton.type="button";
    skillButton.setAttribute("id", "storeButton");
    skillButton.innerHTML="회복";
    skillButton.addEventListener("click", function() {

        const postData = {
            store: "false",
            forge: "false",
            well: "true",
            idxItem: "-1",
            idxStore: "-1"
        }
    
        fetch("./fieldserv", {
            
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
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
        if(document.getElementById("storeButton")){
            document.getElementById("store-button").removeChild(document.getElementById("storeButton"));
        }
        if(document.getElementById("store-div")){
            document.getElementById("store-container").removeChild(document.getElementById("store-div"));
        }
    });
    store.appendChild(skillButton);
}

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

function selectStore() {
    var container = document.getElementById("store-container");
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

const nextButton = document.getElementById("endTurn");
nextButton.addEventListener("click", function() {
    const postData = {
    endStage: "true",
    }
    console.log("포장중");
    fetch("./fieldserv", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(postData)
    })   
        .catch(error => {
            console.error('Error:', error);
    });
    location.href="battle.html";
});