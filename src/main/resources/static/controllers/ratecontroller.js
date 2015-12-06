angular.module('ratemyprofessor').controller('rateCtrl', ['$scope','$q', '$http','$route','$mdDialog',function($scope, $q, $http, $route, $mdDialog){
    var self = this;
	var cid = $route.current.params.cid;
	var uid = $route.current.params.uid;
	
    this.getComments = function(){

    	$http.get('/getComments?cid='+cid).success(function(comments) {
        	
           $scope.comments = comments;
        });
    	
    	$http.get('/getTopProfs?cid='+cid).success(function(topprofs) {
	    	 $scope.topprofs = [];
	         angular.forEach(topprofs, function(value, key){
	        	  $scope.topprofs.push({
	        		    name: key,
	        		    rank: value
	        	  });
	         });
          
           
        });
    	
    	$http.get('/getHotProfs?cid='+cid).success(function(hotprofs) {
        	
    		 $scope.hotprofs = [];
	         angular.forEach(hotprofs, function(value, key){
	        	  $scope.hotprofs.push({
	        		    name: key,
	        		    rank: value
	        	  });
	         });
      
         });
    	
    	$http.get('/getProfStats?cid='+cid).success(function(profstats) {
        	$scope.profstats = profstats;

         });
    }
    
    $scope.rateclass = function(course, ev){
    	$mdDialog.show({
            controller: CommentController,
            templateUrl: '/views/commentDialog.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose:true
          })
              .then(function(answer) {
            	  $route.reload();
              }, function() {
                $scope.status = 'You cancelled the dialog.';
              });
    }
    

    
    this.getComments();
}]);

function CommentController($scope, $mdDialog, $http, $location,$route) {
	var self = this;
	
	$scope.comment ={};

	  $scope.hide = function() {
	    $mdDialog.hide();
	  };
	  $scope.cancel = function() {
	    $mdDialog.cancel();
	  };
	  $scope.answer = function(answer) {
		  $scope.comment.cid = $route.current.params.cid;
		 if($scope.comment.prof === undefined || $scope.comment.comment === undefined 
				 || $scope.comment.rating === undefined || $scope.comment.hotness === undefined 
				 || $scope.comment.grade === undefined || $scope.comment.sleep === undefined 
				 || $scope.comment.texts === undefined){
			 alert("Input all required fields");
			 
			 return;
		 }
		 
		 if($scope.comment.sleep == 'Yes'){
			 $scope.comment.sleep = 'y';
		 }
		 
		 if($scope.comment.sleep == 'No'){
			 $scope.comment.sleep = 'n';
		 }
		 
		 console.log($scope.comment.sleep);
		 var data = $scope.comment;
		 
		 $http.post('/addComment/', data).success(function(data, status) {

         });
			
			 
		 
	    $mdDialog.hide(answer);
	  };
	}