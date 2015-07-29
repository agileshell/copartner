var sendMsg = function() {
    var tempContent = '',
        contentElement = jQuery('input[name="content"]'),
        content = jQuery.trim(contentElement.val()),
        avatar = './images/user_a.jpg';
    if(content) {
        tempContent = '<div class="message-reply"><div class="message-time">' + new Date().toLocaleTimeString() + '</div>'
                    + '<div class="message-info">'
                    + '<div class="user-info"><img class="user-avatar" src="' + avatar + '"></div>'
                    + '<div class="message-content-box"><div class="arrow"></div><div class="item-pics-box"></div>'
                    + '<div class="message-content">' + content + '</div></div></div></div>';
        jQuery('.message-history').append(tempContent);
    }
    contentElement.val('');
};
var initSendMsg = function() {
    // normal submit
    jQuery('.send-msg-btn').on('click', function() {
        sendMsg();
    });
    // Ctrl+Enter submit
    jQuery('input[name="content"]').on('keydown', function(e){
        e = e ? e : window.event;
        if(e.ctrlKey && 13 == e.keyCode){
            sendMsg();
        }
    });
};
initSendMsg();
