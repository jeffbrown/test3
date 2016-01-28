package net.nosegrind.apiframework

import org.grails.core.DefaultGrailsDomainClass
import grails.converters.JSON

class PostController {

	def show(){
		//def post = Post.get(params.id.toLong())
		def test = ["class":"Post","id":1,"author":1,"content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel consequat nisl, quis commodo neque. Integer ultrices vitae nulla lacinia rutrum. Duis ut porta arcu, sed gravida tortor. Donec pulvinar elit turpis, ultricies tristique mi auctor ac. Ut elementum ullamcorper risus ac sollicitudin. Morbi semper ultrices enim vel euismod. Proin eleifend orci ac elit mollis tempor. Nulla egestas odio eu volutpat eleifend. Nunc nec massa eget nisl sodales posuere. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nunc accumsan pretium sapien a tincidunt. Sed at fringilla mi.","creationDate":"2014-08-05T18:45:50Z","currentCnt":0,"endCommentsDate":null,"modifiedDate":"2014-08-05T18:45:50Z","section":["class":"Section","id":6],"stat":null,"teaser":"This is just a test post to see if this works. Testing the api post system.","title":"test post"]
		return test
	}

	def create(){
		List keys = ["content","section","teaser","title"]
		if(params.keySet().contains(keys)){
			return true
		}
		return false
	}


}
