$(function() {
    var availableCodis = [
        "17007",
        "17005",
        "17006",
        "17004",
        "17003",
        "17002",
        "17001",
    ];
    $( "#codi" ).autocomplete({
        source: availableCodis
    });
});