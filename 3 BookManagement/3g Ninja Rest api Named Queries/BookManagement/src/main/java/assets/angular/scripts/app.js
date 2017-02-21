App = {

	startup : function() {

		$("#addBookButton").click(App.addBook);
		$("#loadBookButton").click(App.loadBook);

	},

	loadBook : function() {

		$.getJSON("/book", function(data) {
			var items = [];
			$.each(data, function(key, val) {
				items.push("<li class='list-group-item' id='" + key
						+ "'> <p class='bg-primary'><b>Name</b> &nbsp"
						+ val.bkname + " </p> <ul><li> <b>ISBN</b> &nbsp"
						+ val.isbn + "</li><li> <b>Author ID</b> &nbsp"
						+ val.authorid + "</li><li> <b>Price</b> &nbsp"
						+ val.price + "</li><li> <b>Edition</b> &nbsp"
						+ val.edition
						+ "</li><li> <b>Available Units</b> &nbsp"
						+ val.available_units + "</li></ul></li><br>");
			});
			$("#book").empty();
			$("#book").html(
					"<ul class='list-group'> " + items.join("") + "</ul>");

		});

	}

}

$(document).ready(function() {
	App.loadBook();
	$("form#bookForm").submit(function(e) {
		e.preventDefault();
		$.ajax({
			type : "POST",
			url : "/book",
			data : $("#bookForm").serialize(),
			success : function() {
				App.loadBook();
			}
		});
	});

	$("form#bookdelForm").submit(function(e) {
		e.preventDefault();
		$.ajax({
			type : "POST",
			url : "/delbook",
			data : $("#bookdelForm").serialize(),
			success : function() {
				App.loadBook();
			}
		});
	});

});