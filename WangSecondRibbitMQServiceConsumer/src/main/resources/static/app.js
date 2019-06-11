var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/monitorEndpoint');
    stompClient = Stomp.over(socket);
    var headers = {
    	    username: $("#username").val(),
    	    password: $("#password").val(),
    	   
    	};
    stompClient.connect(headers, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).name);
        });
        stompClient.subscribe('/user/userTest/own', function (greeting) {
            showGreeting(JSON.parse(greeting.body).name);
        });
        stompClient.subscribe('/user/userTest/other', function (greeting) {
            showGreeting(JSON.parse(greeting.body).name);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/hello", {}, JSON.stringify({'name': $("#name").val()}));
}
function sendMyName() {
    stompClient.send("/myhello", {}, JSON.stringify({'name': $("#name").val()}));
}
function sendOther() {
    stompClient.send("/other/"+ $("#otherName").val(), {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function login() {
    var username=$("#username").val();
    var password=$("#password").val();
    $.get("/login?username="+username+"&password="+password);
    	   
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#sendone" ).click(function() { sendMyName(); });
    $( "#sendother" ).click(function() { sendOther(); });
    $( "#login" ).click(function() { login(); });
});