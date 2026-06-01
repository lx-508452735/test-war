let player = 1; // 假设玩家 1
let gameOver = false;

function makeGuess() {
    if (gameOver) return;
    const guess = parseInt(document.getElementById('guessInput').value);
    if (isNaN(guess)) {
        alert('请输入有效的数字');
        return;
    }
    fetch('/game/guess?guess=' + guess + '&player=' + player)
        .then(response => response.text())
        .then(result => {
            const messageElement = document.getElementById('message');
            if (result === '0') {
                messageElement.textContent = '恭喜你，猜对了！你赢了！';
                document.getElementById('guessButton').disabled = true;
                document.getElementById('readyButton').style.display = 'block';
                gameOver = true;
                checkStatus();
            } else if (result === '-1') {
                messageElement.textContent = '猜的数字太小了，再试试。';
            } else {
                messageElement.textContent = '猜的数字太大了，再试试。';
            }
        });
}

function ready() {
    fetch('/game/ready?player=' + player)
        .then(response => response.text())
        .then(result => {
            if (result === 'true') {
                document.getElementById('message').textContent = '双方都准备好了，新游戏开始！';
                document.getElementById('guessButton').disabled = false;
                document.getElementById('readyButton').style.display = 'none';
                document.getElementById('guessInput').value = '';
                gameOver = false;
            } else {
                document.getElementById('message').textContent = '你已准备好，等待对方准备...';
            }
        });
}

function checkStatus() {
    setInterval(() => {
        if (gameOver) {
            fetch('/game/status')
                .then(response => response.json())
                .then(data => {
                    if (!data.gameOver) {
                        document.getElementById('message').textContent = '双方都准备好了，新游戏开始！';
                        document.getElementById('guessButton').disabled = false;
                        document.getElementById('readyButton').style.display = 'none';
                        document.getElementById('guessInput').value = '';
                        gameOver = false;
                    }
                });
        }
    }, 2000);
}