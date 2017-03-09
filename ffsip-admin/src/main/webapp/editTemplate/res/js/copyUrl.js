function BpCopyUrlProcess(contentId) {
    var refreshProcessInterval = 3000;//Unit: ms
    var copyIdTag = BpCopyUrlProcess.CopyIdTag;

    var bpCopyDom = {};
    var toUpdateCopyProcess = {};
    var finishCopyProcess = {};
    var failedCopyProcess = {};
    var updateTimeOut;
    var intervalNo;
    var event = {};

    var object_keys = function(arr) {
        var keys = [];
        for(var i in arr) if(arr.hasOwnProperty(i)) keys.push(i);
        return keys;
    };

    var object_keys_len = function(arr) {
        return object_keys(arr).length;
    };

    var init = function() {
        var contentDom = document.getElementById(contentId);
        if(contentDom){
            console.log("contentId -->"+contentDom.getAttribute("id"));
        }else{
            console.log("contentId not dom");
        }
        var imgDoms = contentDom.getElementsByTagName("img");

        for (var i=0; i < imgDoms.length; ++i) {
            var copyId = imgDoms[i].getAttribute(copyIdTag);
            if(copyId != null) {
                bpCopyDom[copyId] = imgDoms[i];
                toUpdateCopyProcess[copyId] = 'src';
            }
        }
        $("["+BpCopyUrlProcess.CopyIdTagStyle+"]").each(function(){
            var copyId = this.getAttribute('bp_copy_id_style');
            toUpdateCopyProcess[copyId] = toUpdateCopyProcess[copyId] != null ? toUpdateCopyProcess[copyId] + ",style" : "style";
        });
        console.log("toUpdateCopyProcess: "+object_keys_len(toUpdateCopyProcess));
        console.log(toUpdateCopyProcess);
    };

    var refreshProcess = function() {
        if(new Date().getTime() > updateTimeOut) return stopRefresh("timeout");
        getProcess(object_keys(toUpdateCopyProcess));
    };

    var getProcess = function(copyIdArr) {
        if(copyIdArr.length == 0) return stopRefresh("empty copyIdArr");

        $.ajax({
            url : "/urlCopy/getUrlCopyProcess"
            , data : {"copyIds":copyIdArr.join(",")}
            , async : false
            , type : "post"
        }).success(function(data){
            var toDel = [];
            for(var i in toUpdateCopyProcess) {
                if(toUpdateCopyProcess.hasOwnProperty(i) && data[i]) {
                    var process = data[i];
                    onCopyUrlProcessUpdate(i, process, toUpdateCopyProcess[i]);
                    if(process.statusFlag >= 2) {
                        toDel.push(i);
                    }
                }
            }
            for(var i in toDel) {
                delete(toUpdateCopyProcess[toDel[i]]);
            }

            if(object_keys_len(toUpdateCopyProcess) == 0) stopRefresh("empty process");
            else if(event['onRefresh']) event['onRefresh'](getStatus());
        });
    };

    var copyDomSelector = function(copy_id) {
        return "["+copyIdTag+"="+copy_id+"]";
    }

    var onCopyUrlProcessUpdate = function(copyId, process, tag) {
        var ele = bpCopyDom[copyId];
        if(process.statusFlag < 2) {
            if(event["onUpdate"]) event["onUpdate"](ele, process);
        } else if(process.statusFlag == 2 && process.urlCopy) {
            console.log("onCopyUrlProcessUpdate: "+copyId+","+tag);
            finishCopyProcess[copyId] = process;
            var newUrl = process.urlCopy.copyUrl;
            var width = process.urlCopy.fileWidth;
            var height = process.urlCopy.fileHeight;
            if(/src/.test(tag)) {
                $(copyDomSelector(copyId)).attr("src", newUrl);
                $(copyDomSelector(copyId)).attr("width", width);
                $(copyDomSelector(copyId)).attr("height", height);
                $(copyDomSelector(copyId)).removeAttr(BpCopyUrlProcess.CopyIdTag);
                console.log("Done "+copyDomSelector(copyId));
            }
            if(/style/.test(tag)) {
                $("["+BpCopyUrlProcess.CopyIdTagStyle+"="+copyId+"]").each(function(){
                    if($(this).attr('style')) {
                        $(this).attr('style', $(this).attr('style').replace("bpCopy://"+copyId, newUrl));
                    }
                }).removeAttr(BpCopyUrlProcess.CopyIdTagStyle);
            }
            //ele.setAttribute("src", process.urlCopy.copyUrl);
            //ele.removeAttribute(copyIdTag);
            if(event["onFinish"]) event["onFinish"](ele, process);
        } else {
            failedCopyProcess[copyId] = process;
            if(event["onFail"]) event["onFail"](ele, process);
        }
    };

    var getStatus = function() {
        return {
            "all" : object_keys_len(bpCopyDom),
            "finish" : object_keys_len(finishCopyProcess),
            "fail" : object_keys_len(failedCopyProcess),
            "update" : object_keys_len(toUpdateCopyProcess)
        };
    };

    this.status = function(){ return getStatus()};

    this.isUpdating = function() {
        return object_keys_len(toUpdateCopyProcess) > 0 || intervalNo != undefined;
    };

    var bindEvent = function(eventName, func) {event[eventName] = func; return this};
    this.onFinish = function(func) {return bindEvent("onFinish", func);};
    this.onUpdate = function(func) {return bindEvent("onUpdate", func);};
    this.onFail = function(func) {return bindEvent("onFail", func);};
    this.onAllEnd = function(func) {return bindEvent("onAllEnd", func);};
    this.onRefresh = function(func) {return bindEvent("onRefresh", func);};

    this.start = function() {startRefresh()};
    this.stop = function() {stopRefresh("external call stop");};

    this.dump = function() {
        console.log(bpCopyDom);
        console.log("ToUpdate:", toUpdateCopyProcess);
        console.log("Finish:", finishCopyProcess);
        console.log("Fail:", failedCopyProcess);
    }

    var startRefresh = function() {
        updateTimeOut = new Date().getTime() + 600 * 1000;
        if(intervalNo == undefined) {
            intervalNo = setInterval(refreshProcess, refreshProcessInterval);
            setTimeout(refreshProcess, 1);
            setTimeout(refreshProcess, 1000);
        }
    }

    var stopRefresh = function(reason) {
        if(intervalNo) {
            console.log("stop refresh, caused by: "+reason);
            window.clearInterval(intervalNo);
            intervalNo = undefined;
            if(event["onAllEnd"]) event["onAllEnd"](getStatus());
        }
    }
    init();
}
BpCopyUrlProcess.CopyIdTag = "bp_copy_id";
BpCopyUrlProcess.CopyIdTagStyle = "bp_copy_id_style";
BpCopyUrlProcess.isCopying = function(node) {
    return $(node).attr(BpCopyUrlProcess.CopyIdTag) != undefined;
}