let socket = new SockJS("http://localhost:8080/chat")
let client = Stomp.over(socket)

var key= 'abc123XYZ';
function uuidv4() {
    return ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c =>
        (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );
}

const ID = uuidv4()

function createMessage(content, recipient) {

    return {
        senderId: ID,
        recipientId: recipient,
        senderName: "esad",
        recipientName: "akcam",
        content: content.toString(),
        timestamp: Date.now(),
    };
}

const fetchMessage = {senderId: ID, from: Date.now()}
client.connect({}, (frame) => {
    console.log("connected" + frame)
    client.subscribe("/user/" + ID.toString() + "/queue/messages", (msg) => {
        console.log("msg:")
        console.log(msg)
        document.getElementById("messages").innerText += "\n" + msg.body;
    })
})

document.getElementById("app").innerText = ID;

document.getElementById("btn").addEventListener("click", () => {
    let input = document.getElementById("inp").value;
    console.log("input: " + input);
    let recipient = document.getElementById("recipient").value;
    console.log("recipient: " + recipient);
    let message = createMessage(input, recipient);
    console.log("message : " + message);
    client.send("/app/messageTopic", {}, JSON.stringify(message))
})

document.getElementById("session-key").addEventListener("click", () => {
    let input = document.getElementById("inp").value;

    let message = {
       senderId: ID,
       sessionKey: "esa123d"
    }
    client.send("/app/sessionKey", {}, JSON.stringify(message))
})
document.getElementById("fetch").addEventListener("click", () => {
    let date = document.getElementById("date").value
    console.log(date);
    let message = {senderId: ID, from: date};
    client.send("/app/fetchTopic", {}, JSON.stringify(message));
    console.log("sent: ", message)
})

document.getElementById("ssl-handshake").addEventListener("click", () => {
    let message = {senderId: ID}
    console.log(message)
    client.send("/app/sslHandshake", {}, JSON.stringify(message))
})

