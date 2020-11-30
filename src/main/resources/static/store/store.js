angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/market';

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/product',
            method: 'GET',
            params: {
                title_part: $scope.filterProduct ? $scope.filterProduct.title_part : null,
                min_price: $scope.filterProduct ? $scope.filterProduct.min_price : null,
                max_price: $scope.filterProduct ? $scope.filterProduct.max_price : null,
                page: pageIndex
            }
        })
            .then(function (response) {
                $scope.ProductsPage = response.data;
                $scope.PaginationArray = $scope.generatePageIndexes(1, $scope.ProductsPage.totalPages);
            })
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/product', $scope.newProduct)
            .then(function (response) {
                $scope.fillTable();
                $scope.newProduct.title = null;
                $scope.newProduct.price = null;
            })
    };

    $scope.deleteProduct = function (product) {
        $http.delete(contextPath + '/api/v1/product/' + product.id)
            .then(function (response) {
                $scope.fillTable();
            })
    };

    $scope.generatePageIndexes = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    };

    $scope.fillTable();
});