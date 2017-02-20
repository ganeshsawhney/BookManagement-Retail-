$(document).ready(function() {
	$(".editInput").hide();
	$(".updateSave").hide();
});

$(document).on("click", ".updateButton", function() {
	$("." + this.id + "text").hide();
	$("#" + this.id).hide();
	$("#" + this.id + "updateSave").show();
	$("." + this.id + "input").show();
});

$(document).on("click", ".updateSave", function() {
	var str = this.id;
	var id = str.substring(0, str.length - 10);
	$("." + id + "text").show();
	$("#" + id).show();
	$("#" + id + "updateSave").hide();
	$("." + id + "input").hide();
});
