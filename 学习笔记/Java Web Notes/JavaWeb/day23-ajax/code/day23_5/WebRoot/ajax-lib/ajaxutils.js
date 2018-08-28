function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();
	} catch (e) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				return new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("哥们儿，你用的是什么浏览器啊？");
				throw e;
			}
		}
	}
}

/*
 * 需要给出一个对象:option
 * 这个对象包括的属性：
 * 	请求方式：method
 * 	请求的url：url
 * 	是否异步：asyn
 * 	请求体：params
 * 	回调方法：callback
 * 		即：获取到服务器发过来的数据后，
 * 			如何对这个数据进行处理的方法。
 * 			这个方法让调用者自行编写，然后传递过来，我们直接调用即可
 * 	服务器响应数据转换成什么类型（服务器响应的数据是什么类型：text、xml、json）：type
 */
function ajax(option) {
	//1.得到异步对象
	var xmlHttp = createXMLHttpRequest();
	
	/*
	 * 2.打开连接
	 * 	首先判断请求法方式，如果没有给出请求方式这个属性，默认使用get请求
	 * 	判断是否异步：如果没有给出是否异步的属性asyn，默认asyn为true
	 * */
	if(!option.method) {//如果没有这个属性，返回的是undefined，取反后为true。同这个语法一样：option.method == undefined
		//如果在option对象中，method属性没有给出，默认为get请求方式
		option.method = "GET";
		option.params = null;//并且请求体为null
	}
	if(option.asyn == undefined) {
		//如果option中，没有给出是否异步(asyn)这个属性，默认为：异步处理
		option.asyn = true;
	}
	xmlHttp.open(option.method, option.url, option.asyn);
	
	/*
	 * 3.发送请求
	 * 	首先判断请求方式是否为post，如果是，就需要加一个请求头
	 * */
	if(option.method == "POST") {
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	}
	//如果是get请求，并且调用者还给出了请求体。也不用担心，因为get请求是不会带上请求体的
	xmlHttp.send(option.params);
	
	//4.给异步队形的状态改变事件注册监听器
	xmlHttp.onreadystatechange = function() {
		//双重判断，如果xmlHttp的状态为响应结束，并且服务器响应成功
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			/*
			 * 5.获取服务器响应的数据
			 * 	判断响应的数据是什么:option.type
			 * 	如果option对象没有给出type属性，那么默认获取的响应数据为text
			 * */
			var data;
			if(!option.type) {
				data = xmlHttp.responseText;
			} else if(option.type == "xml") {
				data = xmlHttp.responseXML;
			} else if(option.type == "text") {
				data = xmlHttp.responseText;
			} else if(option.type = "json") {
				//获取到服务器响应过来的json串
				var text = xmlHttp.responseText;
				//执行一遍，变成对象
				data = eval("(" + text +  ")");
			}
			
			//调用回调方法,即：把从服务器得到的数据，交给这个方法执行,这个方法由调用者给出
			option.callback(data);
		}
	};
}