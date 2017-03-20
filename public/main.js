function getFiles(allUsers) {
    for (var i in allUsers) {
        var elem = $("<a>");
        elem.attr("href", "/user/" + allUsers[i].id);
        elem.text(allUsers[i].name);
        $("#users").append(elem);
        var elem2 = $("<br>");
        $("#users").append(elem2);
    }
}

$.get("/user", getFiles);

