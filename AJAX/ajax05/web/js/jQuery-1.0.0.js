
function jQuery(selector){
    if (typeof selector== "string"){
        if (selector.charAt(0) == "#") {
            node = document.getElementById(selector.substring(1));
            return new jQuery()
        }
    }
    if (typeof selector == "function"){
    window.onload=selector
    }
    this.html=function (html){
         node.innerHTML=html
    }
    this.click=function(fun){
        node.onclick=fun
    }
    this.val=function (){
        var value = node.value;
        return value
    }
    this.change=function (fun){
        node.onchange=fun
    }
    this.keyup = function (fun){
        node.onkeyup=fun
    }
    jQuery.ajax=function (jsonArgs){
        var xhr = new XMLHttpRequest()
        xhr.onreadystatechange=function (){
            if (this.readyState == 4) {
                if (this.status == 200) {
                    var jsonObj = JSON.parse(this.responseText)
                    jsonArgs.success(jsonObj)
                }else{
                 alert(this.status)
                 }
            }
       }
        if (jsonArgs.type.toUpperCase() == "POST") {
            xhr.open("POST",jsonArgs.url,jsonArgs.async)
            //该行模拟表单提交数据，只能出现在open之后，send之前
            xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
            xhr.send(jsonArgs.data)
        }
        if (jsonArgs.type.toUpperCase()=="GET"){
            xhr.open("GET",jsonArgs.url+"?"+jsonArgs.data,jsonArgs.async)
            xhr.send()
        }
    }
}
new jQuery()
$=jQuery