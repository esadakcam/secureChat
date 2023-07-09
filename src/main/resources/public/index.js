let socket = new SockJS("http://localhost:8080/chat")
let client = Stomp.over(socket)
let id = 1;

function uuidv4() {
    return ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c =>
        (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );
}
const ID = uuidv4()
const message = {
    senderId: ID,
    recipientId: ID,
    senderName: "esad",
    recipientName: "akcam",
    content: "deneme",
    timestamp: Date.now(),
};
const fetchMessage = {senderId: message.recipientId, from: Date.now()}
client.connect({}, (frame) => {
    console.log("connected" + frame)
    client.subscribe("/user/" + message.recipientId.toString() + "/queue/messages", (msg) => {
        console.log("msg:")
        console.log(message.content)
    })
})

