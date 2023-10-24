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
            checkUse(data.field);
        })
        .catch(error => {
            console.error('Error:', error);
    });
};

function checkUse(field) {
    const storeButton = document.getElementById("store");
    const forgeButton = document.getElementById("forge");
    const wellButton = document.getElementById("well");

    if (field.storeCount>0) {
        storeButton.innerHTML = "상점("+field.storeCount+")";
    } else {
        storeButton.innerHTML = "상점";
    }
    if (field.forgeCount>0) {
        forgeButton.innerHTML = "대장간("+field.forgeCount+")";
    } else {
        forgeButton.innerHTML = "대장간";
    }
    if (field.healCount>0) {
        wellButton.innerHTML = "회복("+field.healCount+")";
    } else {
        wellButton.innerHTML = "회복";
    }
}

const storeList = document.getElementById("store");
storeList.addEventListener("click", function(){
    const gridBackground = document.getElementById("field-background");
    
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
                gridBackground.style = "background-image : linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.5) ), url(./image/back6.jpg)";
            } else {
                storeContainer.innerHTML="모든 횟수를 소진했습니다"
                gridBackground.style = "background-image : linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.5) ), url(./image/shop.jpg)";
            }
        })
        .catch(error => {
            console.error('Error:', error);
    });
    
});

const mousePosition = {x:0,y:0,isclick:false};

//스토어
const storeContainer = document.getElementById("store-container");
function setStoreList(list) {
    storeContainer.innerHTML = "";
    for (let i = 0; i < list.length; i++) {
        let itemElement = document.createElement("div");
        itemElement.className = "store-div";
        itemElement.innerHTML = "<span style='font-weight:bold'>"+list[i].name+"</span><br><br>";
        itemElement.innerHTML +=list[i].description+"<br><br>";
        if (list[i].times>1) {
        	itemElement.innerHTML +="남은횟수 : "+list[i].times+"<br><br>";
        }
		if (list[i].count>0) {
        	itemElement.innerHTML +="카운트 : "+list[i].count+"<br><br>";
        }
		itemElement.classList.add("itemBasic");
        
        itemElement.addEventListener("click", function() {
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
        // itemElement.onmousedown=(e)=>{
        //     console.log(e);
        //     console.log("마우스클릭");
        //     mousePosition.x=e.clientX;
        //     mousePosition.y=e.clientY;
        //     mousePosition.isclick = true;
        // }
        
        // itemElement.onmousemove=(e)=>{
        //     if (mousePosition.isclick) {
        //         itemElement.style.transform = `translate(${e.clientX-mousePosition.x}px, ${e.clientY-mousePosition.y}px)`;
        //         console.dir(e.clientX);
        //     }
        // }
        // itemElement.onmouseup=(e)=>{
        //     mousePosition.isclick = false;
        //     e.target.style.transform = "";
        //     console.log("마우스클릭해제");
        // }
        ronundOver(itemElement)
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
    skillButton.innerHTML="구입";
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
            setStoreList(data.field.store.storeList);
            checkUse(data.field);
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
    playerElement.innerHTML = player.job + "  Level " + player.level + "<br>"
    playerElement.innerHTML += "HP: "+player.hp;
    if (player.def>0) { 
		playerElement.innerHTML += " <span style='color:gold; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;'>("+player.def+")</span>";
	}
    playerElement.innerHTML += " / " + player.maxHp + "<br>"
    playerElement.innerHTML +=" Dice:" + player.diceQuantity +  " SP:" + player.sp + "<br>"
    playerElement.innerHTML +=" [ EXP : " + player.exp +  " / " + player.expTable[player.level] + " ]<br>"
    playerContainer.appendChild(playerElement);
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
        if (list[i].name=="빈슬롯"){
			itemElement.style.opacity = 0.5;
		}
        itemElement.innerHTML = "<span style='font-weight:bold'>"+list[i].name+"</span><br><br>";
        itemElement.innerHTML +=list[i].description+"<br><br>";
        if (list[i].times>1) {
        	itemElement.innerHTML +="남은횟수 : "+list[i].times+"<br><br>";
        }
		if (list[i].count>0) {
        	itemElement.innerHTML +="카운트 : "+list[i].count+"<br><br>";
        }
		itemElement.classList.add("itemWhite");
        
        ronundOver(itemElement);        
        
        itemElement.addEventListener("click", function () {
        if (itemElement.classList.contains("itemWhite")) {
            console.log("검은색");
            resetItemColor(list);
            itemElement.classList.remove("itemWhite");
            itemElement.classList.add("itemRed");
        } else if (itemElement.classList.contains("itemRed")) {
            console.log("빨강");
            itemElement.classList.remove("itemRed");
            itemElement.classList.add("itemWhite");
        } else {
            console.log("안됨");
        }
      });
      itemContainer.appendChild(itemElement);
    }
}

//마우스오버시 테두리
function ronundOver(itemElement) {
    const span = itemElement.querySelector("span");
    itemElement.onmouseover=(e)=>{
        e.target.style = "box-shadow: 0 0 7px 5px black;"
        span.style = "border: 0px solid #000; font-weight: bold;;"
    }

    itemElement.onmouseleave=(e)=>{
        e.target.style = "box-shadow:;"
        span.style = "border: 0px solid #000; font-weight: bold;;"
        if (e.target.innerHTML.includes("빈슬롯")) {
            e.target.style.opacity = 0.5;
        }
    }
}

function resetItemColor(list) {
    for (let i = 0; i < list.length; i++) {
        document.getElementsByClassName("item-div")[i].classList.remove("itemRed");
        document.getElementsByClassName("item-div")[i].classList.add("itemWhite");
    }
}


const forge = document.getElementById("forge");
forge.addEventListener("click", function(){
    const gridBackground = document.getElementById("field-background");
    
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
                printForge()
                gridBackground.style = "background-image : linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.5) ), url(./image/anvil.jpg)";
                // createForgeButton();
            } else {
                storeContainer.innerHTML="모든 횟수를 소진했습니다"
                gridBackground.style = "background-image : linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.5) ), url(./image/shop.jpg)";
            }
        })
        .catch(error => {
            console.error('Error:', error);
    });
    if(document.getElementById("storeButton")){
        document.getElementById("store-button").removeChild(document.getElementById("storeButton"));
    }
    
});

function printForge() {
    const forgeContainer = document.getElementById("store-container");
    forgeContainer.innerHTML="";
    let forgeInfo = document.createElement("div");
    forgeInfo.className = "forge-div";
    forgeInfo.classList.add("rounded-3");
    forgeInfo.classList.add("bg-secondary");
    forgeInfo.classList.add("bg-gradient"); 
    forgeInfo.classList.add("text-white");
    forgeInfo.innerHTML ="<span style='font-weight:bold'>강화할 아이템을 선택하세요</span>";
    forgeContainer.appendChild(forgeInfo);
}

function setForge(list, index) {
    const forgeContainer = document.getElementById("store-container");
    forgeContainer.innerHTML="";
    let forgeInfo = document.createElement("div");
    forgeInfo.className = "forge-div";
    forgeInfo.classList.add("rounded-3");
    forgeInfo.classList.add("bg-secondary");
    forgeInfo.classList.add("bg-gradient"); 
    forgeInfo.classList.add("text-white");
    // forgeInfo.style.color="white";
    forgeInfo.innerHTML ="<span style='font-weight:bold'>"+list[index].enhName+"</span><br><br>";
    forgeInfo.innerHTML += list[index].enhDescription+"<br />";
    if (list[index].enhCount>0) {
        forgeInfo.innerHTML += "<br />카운트 : "+list[index].enhCount+"<br />";
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
        itemElement.classList.add("text-white");
        if (list[i].name=="빈슬롯"){
			itemElement.style.opacity = 0.5;
		}
        itemElement.innerHTML = "<span style='font-weight:bold'>"+list[i].name+"</span><br><br>";
        itemElement.innerHTML +=list[i].description+"<br><br>";
        if (list[i].times>1) {
        	itemElement.innerHTML +="남은횟수 : "+list[i].times+"<br><br>";
        }
		if (list[i].count>0) {
        	itemElement.innerHTML +="카운트 : "+list[i].count+"<br><br>";
        }
		itemElement.classList.add("itemWhite");
        
        itemElement.addEventListener("click", function () {
        if (itemElement.classList.contains("itemWhite")) {
            console.log("검은색");
            resetItemColor(list);
            itemElement.classList.remove("itemWhite");
            itemElement.classList.remove("text-white");
            itemElement.classList.add("itemRed");
            selectItem();
            setForge(list, itemNum);
            createForgeButton();
        } else if (itemElement.classList.contains("itemRed")) {
            console.log("빨강");            
            itemElement.classList.remove("itemRed");
            itemElement.classList.add("text-white");
            itemElement.classList.add("itemWhite");
        } else {
            console.log("안됨");
        }
      });
      ronundOver(itemElement);
      itemContainer.appendChild(itemElement);
    }
}

function createForgeButton() {
    if(document.getElementById("storeButton")){
        document.getElementById("store-button").removeChild(document.getElementById("storeButton"));
    }
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
            checkUse(data.field);
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
    
    const gridBackground = document.getElementById("field-background");
    
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
                itemElement.innerHTML="체력을 ["+(data.player.level+4)+"] 회복합니다<br>"
                itemElement.style.color="white";
                createWellButton()
                gridBackground.style = "background-image : linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.5) ), url(./image/well.jpg)";
            } else {
                itemElement.classList.remove("well-div")
                itemElement.classList.remove("bg-success")
                itemElement.classList.remove("bg-gradient")
                itemElement.innerHTML="모든 횟수를 소진했습니다"
                gridBackground.style = "background-image : linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.5) ), url(./image/shop.jpg)";
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
            checkUse(data.field);
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
    let  container = document.getElementById("item-container");
    let  items = container.getElementsByTagName('div');

    itemNum=-1;
    for (let  i = 0; i < items.length; i++) {
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
    let  container = document.getElementById("store-container");
    let  items = container.getElementsByTagName('div');

    let itemNum=-1;
    for (let  i = 0; i < items.length; i++) {
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