console.log("Reply Module.....");

//즉시  실행함수
let replyService = (function(){

    function add(reply, callback, error){      //reply에 get{}값 전달, callback(함수 자체 전달) : function(result){} 전달
        console.log("add reply...");

        $.ajax({
            //controller에서 해당 경로에 데이터 값 저장(@RequestBody)
            type: 'post',   //경로 post방식
            url: '/replies/new',    //경로
            data: JSON.stringify(reply),    //reply(js객체) -> json으로 변환
            contentType: "application/json; charset=utf-8",

            //"success"인 경우 아래 함수 실행(result = "success"), result값을 get.jsp의 함수 매개변수 result에 전달
            success: function(result, status, xhr){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        });
    } //end add

    function getList(param, callback, error){
        let bno = param.bno;

        let page = param.page || 1;     //param.page 값이 없는 경우 1 대입

        $.ajax({
        	//요청 Controller에게 전달
            type: 'get',
            url: '/replies/pages/' + bno + "/" + page,
			
			//Controller의 return 값 result에 저장되어O
            success: function(result, status, xhr){
                if(callback){
                    callback(result.replyCnt, result.list);
                    console
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        });
    } //end getList(전체 데이터 조회)

    function remove(rno, callback, error){
        $.ajax({
            type: 'delete',
            url: '/replies/' + rno,

            success: function(deleteResult, status, xhr){
                if(callback){
                    callback(deleteResult);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        });
    } //end remove

    function get(rno, callback, error){
        $.ajax({
            type: 'get',
            url: '/replies/' + rno,

            success: function(result, status, xhr){
                if(callback){
                    callback(result);
                }

            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    } //end get(단건 데이터 조회)

    function update(reply, callback, error){
        $.ajax({
            type: 'put',
            url: '/replies/' + reply.rno,
            data: JSON.stringify(reply),    //reply(js객체) -> json으로 변환하여 data에 넣음
            contentType: "application/json; charset=utf-8",     //전송 타입

            success: function(result, status, xhr){     //result: 응답 데이터("success")
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    } //end update
    
    function displayTime(timeValue){

        let today = new Date();
        let gap = today.getTime() - timeValue;

        let dataObj = new Date(timeValue);
        let str = "";

        if(gap < (1000*60*60*24)){
            let hh = dataObj.getHours();
            let mi = dataObj.getMinutes();
            let ss = dataObj.getSeconds();

            return [ (hh > 9 ? '' : '0') + hh, ":",
                     (mi > 9 ? '' : '0') + mi, ":",
                     (ss > 9 ? '' : '0') + ss
                    ].join('');
        }else{
            let yy = dataObj.getFullYear();
            let mm = dataObj.getMonth();
            let dd = dataObj.getDate();

            return [
                    yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') +dd
                    ].join('');
        }
    } //end displayTime


    return {
        add:add,
        getList: getList,
        remove: remove,
        get: get,
        update: update,
        displayTime: displayTime,
    };
})();
