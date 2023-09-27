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