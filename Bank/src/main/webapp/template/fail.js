/**
 * Created by Jasmina on 21/01/2018.
 */
app.controller('FailController', function ($scope, $state, cardPaymentService){

    var loadName = function () {
        cardPaymentService.getBankName(function(response){
            $scope.bankName = response.data.name;
        });
    }

    loadName();

    $scope.imagePath = "image/error.png";
    $scope.cardPath = "image/cards.png";
});