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
        })
    } //end add

    function getList(param, callback, error){
        let bno = param.bno;

        let page = param.page || 1;     //param.page 값이 없는 경우 1 대입

        $.ajax({
            type: 'get',
            url: '/replies/pages/' + bno + "/" + page,

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
        })
    } //end getList

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
        })
    } //end remove

    function update(reply, callback, error){
        
    }

    return {
        add:add,
        getList: getList,
        remove: remove,
    };
})();
