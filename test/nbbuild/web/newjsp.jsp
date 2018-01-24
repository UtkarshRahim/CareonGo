<html>
<head>
    <title></title>
</head>
<body>
    
    <script type="text/javascript">
        var app = angular.module('MyApp', []);
        app.controller('MyController', function ($scope) {
            //This will hide the DIV by default.
            $scope.IsVisible = false;
            $scope.ShowHide = function () {
                //If DIV is visible it will be hidden and vice versa.
                $scope.IsVisible = $scope.ShowSubmit;
            };
        });
    </script>
    <div ng-app="MyApp" ng-controller="MyController">
        <label for="chkPassport">
            <input type="checkbox" id="chk" ng-model="ShowSubmit" ng-change="ShowHide()" />
            I agree to the terms and conditions !
        </label>
        <hr />
        <div ng-show="IsVisible">
            Passport Number:
            <input type="submit" id="Proceed" />
        </div>
    </div>
</body>
</html>