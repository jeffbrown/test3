# testapp

in BatchInterceptor we set:

        if (!request.getAttribute('batchInc')) {
            request.setAttribute('batchInc',0)
        }else{
            request.setAttribute('batchInc',request.getAttribute('batchInc').toInteger() + 1)
        }
        if(!request.getAttribute('batchLength')){ request.setAttribute('batchLength',3) }
        

'batchLength' establishes loop size
'batchInc' increments with each pass of the loop


curl --verbose --request GET --header "Content-Type: application/json" "http://localhost:8080/v0.1/post/show/1" --cookie cookies.txt

Application should loop THREE TIMES. Loops infinitely.
