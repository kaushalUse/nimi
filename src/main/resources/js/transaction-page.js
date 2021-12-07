$(document).ready(function () {


});


$('#transferBtn').click(function() {
    var fromLedger = $("#fromLedger").val();
    var toLedger = $("#toLedger").val();
    var amount = $("#amount").val();
    var comment = $("#type option:selected" ).text();
    var type = $("meta[name='_csrf']").attr("content");
    var transactionRequest = JSON.stringify({
        fromLedger: fromLedger,
        toLedger: toLedger,
        amount: amount,
        type:type
    });
    $.ajax({
        url: 'create-transaction',
        headers: {"X-CSRF-TOKEN": token},
        type: 'POST',
        contentType: "application/json",
        data: transactionRequest,
        dataType: "json",
        success: function (result) {
            if (result != null) {
                if (result.data !== undefined && result.data != null) {
                    if (result.data.isPartiallyCompleted == true) {
                        return;
                    } else {
                        showCardLoadInfo("cardDetailPage.info.card.loaded");
                        showNewBalance();
                        return;
                    }
                }
                if (result.errors != null && result.errors[0] != null) {
                    return;
                }
            }
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
 })

