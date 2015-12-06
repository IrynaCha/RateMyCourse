angular.module('ratemyprofessor').controller('mainCtrl', ['$scope','$q', '$http','$location','$route','$mdDialog',function($scope, $q, $http, $location,$route,$mdDialog){
 var url = 'http://127.0.0.1:8080/';
var self = this;
 self.selectedItem = '';
    self.showTable = false;
    self.selectedUniversity = '';
// self.courses ='';
    
 this.fetchList = function() {
	     $http.get('/getTopUniversities').success(function(topuniversities) {
	    	 $scope.topunivs = [];
	         angular.forEach(topuniversities, function(value, key){
	        	 if(value == 'NaN'){
	        		value = 0; 
	        	 }
	        	  $scope.topunivs.push({
	        		    name: key,
	        		    rank: value
	        	  });
	         });
	         
	     });
	     
        $http.get('/getUniversities').success(function(universities) {
            self.universities = universities;
        });
     
        $http.get('/getDepartments').success(function(departments) {
            self.departments = departments;
            
        });
        

        
    };

    
    this.selectedItemChange = function(selectedUniversity){
        if(selectedUniversity === undefined){
            return;
        }
        self.getcourses(selectedUniversity.uid);
        
        
    }
    
    this.getcourses = function(universityid){
    	$http.get('/getCoursesByUniv?uid='+universityid).success(function(courses) {
        	$location.path('/university/'+universityid);
            self.courses = courses;
            self.showTable = true;
           
        });
    }
    
    this.addCourse = function(ev){
        $mdDialog.show({
            controller: DialogController,
            templateUrl: '/views/CourseDialog.html',
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
 
    $scope.$watch(
            function() {
                return $route.current;
            },
            function(newValue) {
            	if(newValue && newValue.$$route){
            		$scope.univtemplate = newValue.$$route.originalPath.replace(':uid', '');
            		$scope.ratetemplate = newValue.$$route.originalPath.replace(':uid/:cid', '');
            		
                    if($scope.univtemplate == '/university/'){
                    	
                    	self.getcourses(newValue.params.uid);
                    	$http.get('/getUniversities').success(function(universities) {

	                        universities.forEach(function(university){
	                        	if(newValue.params.uid == university.uid){
	                        		self.selectedUniversity = university.name;
	                        	}
	                        });
                    	});
                    }
                    
                    if($scope.ratetemplate == '/rate/'){

                    	if(self.courses == undefined){
                    		$http.get('/getCoursesByUniv?uid='+newValue.params.uid).success(function(courses) {
                                self.courses = courses;  
                                self.courses.forEach(function(course){
                                	if(course.cid == newValue.params.cid){
                                		self.selectedCoursevar = course;
                                	}
                                });
                            });
                    	}
                    	$http.get('/getUniversities').success(function(universities) {

	                        universities.forEach(function(university){
	                        	if(newValue.params.uid == university.uid){
	                        		self.selectedUniversity = university.name;
	                        	}
	                        });
                    	});
                    }
            	}
            }
        );
    this.selectedCourse = function(course){
        var uid = $route.current.params.uid;
        self.universities.forEach(function(university){
        	if(uid == university.uid){
        		self.selectedUniversity = university.name;
        	}
        });
        self.selectedCoursevar = course;
        $location.path('/rate/'+ uid + '/'+ course.cid);

        
    }
    
    $scope.homepage = function(){
    	 $location.path('/');
    	 self.showTable = false;
    }
this.fetchList();
    
}]);
function DialogController($scope, $mdDialog, $http, $location,$route) {
	var self = this;
	$http.get('/getDepartments').success(function(departments) {
        $scope.departments = departments;
        
    });
	
    $scope.course ={};
	  $scope.hide = function() {
	    $mdDialog.hide();
	  };
	  $scope.cancel = function() {
	    $mdDialog.cancel();
	  };
	  $scope.answer = function(answer) {
		  
		   if($scope.course.name === undefined || $scope.course.num === undefined || $scope.course.dname === undefined){
			   alert("Input all required fields");
			   return;
		   }

		  var deptname = $scope.course.dname.trim();
		
		  $scope.course.dname = deptname;
		  $scope.course.uid = $route.current.params.uid;
		   var universityid = $scope.course.uid;
		   var data = $scope.course;
		   
		   
    	$http.get('/getCoursesByUniv?uid='+universityid).success(function(courses) {
        	$location.path('/university/'+universityid);
            self.courses = courses; 
            var found = false;
            
          for(i = 0 ; i< courses.length; i++){
        	  if(courses[i].num == $scope.course.num && courses[i].dname ==  $scope.course.dname){
        		  found = true;
        	  }
          } 
          if(found == false){
            $http.post('/addCourse/', data).success(function(data, status) {

            });
          }
	  
        });

	    $mdDialog.hide(answer);
	  };
	}