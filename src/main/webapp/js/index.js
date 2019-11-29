$(function(){
	$("aside ul li.active").click(function() {
		var nodeId = this.id;
		var subNodes = $("aside ul li[parentId="+nodeId+"]");
		if(subNodes.css("display")=="none") {
			//subNodes.css("display", "block");
			subNodes.slideDown();
		} else {
			//subNodes.css("display", "none");
			subNodes.slideUp();
		}
	});
});
