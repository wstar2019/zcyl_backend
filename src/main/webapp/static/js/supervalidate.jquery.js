var FormValidate=function(ele,opt) {
    this.$element = ele;
    this.defaults={
        //'phone':/(^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$)/,
        //'phone':/(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\(\d{3}\))|(\d{3}\-))?(1[358]\d{9})$)|\d{5}/,
    	'phone':/^\d+$/,
        'email':/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/,
        'idcard':/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}[\d\w]{1}$/,
        'date':/^[1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9]$/,
        'datetime':/^[1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9](\s*[0-2][0-9]:[0-6][0-9](:[0-6][0-9])?)?$/,
        'string':/.+/,
        'url':/^https?:\/\/(([a-zA-Z0-9_-])+(\.)?)*(:\d+)?(\/((\.)?(\?)?=?&?[a-zA-Z0-9_-](\?)?)*)*$/i,
        'number':/^\d+$/,
        'double':/^\d+(\.\d*)?$/,
        'phone2':/^(\d+)(\-\d+)*$/,
        'age':/^[1-9]$|^[1-9]\d$|^1[0-2]\d$|^130$/
    }
    this.settings = $.extend({},this.defaults,opt);
};
FormValidate.prototype = {
    options:{
        phone:'请输入正确的手机号',
        email:'请输入正确的邮箱',
        idcard:'请输入正确的身份证',
        date:'请输入正确的日期',
        datetime:'请输入正确的时间',
        string:'请输入正确的值',
        url:'请输入正确的url',
        number:'请输入数字',
        double:'请输入数字',
        phone2:'请输入正确的电话',
        age:'请输入正确的年龄',
    },
    validate:function(){
        var flag = true;
        function validLength(length,value) {
            if(length&&value) {
                return value.length <= length*1;
            }
            return true;
        }

        $("input[max-length]").each(function(i,e){
            var max_length=$(this).attr("max-length");
            var value = $(this).val();
            if(!validLength(max_length,value)) {
                flag = false;
                $(this).addClass("error")
                $(this).unbind("blur");
                $(this).blur(function() {
                    var length=$(this).attr("max-length");
                    var v=$(this).val();
                    if(validLength(length,v)) {
                        $(this).removeClass("error");
                    }
                });
            }

        });
       for(var key in this.settings) {
           var pattern = this.settings[key];
           this.$element.find("." + key+":enabled").each(function() {
              if(($(this).val()||$(this).attr("class").indexOf("required")>-1)&&!pattern.test($(this).val())) {
                  $(this).addClass("error");
                  flag=false;
                  this.spattern = pattern;
                  $(this).keyup(function(){
                    if((!$(this).val()&&$(this).attr("class").indexOf("required")>-1)||eval(this.spattern).test($(this).val())){
                        $(this).removeClass("error");
                    }else{
                        $(this).addClass("error")
                    }
                  });
              }
           });

       }

        function validateAge() {
            var bage = $(".bage").val();
            var eage = $(".eage").val();
            if(bage&&eage) {
                return eage > bage;
            }
            return true;
        }

        if(!validateAge()) {
            $(".bage,.eage").addClass("error");
            $(".bage,.eage").unbind("change");
            $(".bage,.eage").change(function() {
                if(validateAge()) {
                    $(".bage,.eage").removeClass("error");
                }
            });
            flag = false;
        }

        function validateTime() {
            var begin = $(".begin").val();
            var end = $(".end").val();
            if(begin&&end) {
                var b = new Date(begin);
                var e = new Date(end);
                return e > b;
            }
            return true;
        }

        if(!validateTime()) {
            $(".begin,.end").addClass("error");
            $(".begin,.end").unbind("change");
            $(".begin,.end").change(function() {
                if(validateTime()) {
                    $(".begin,.end").removeClass("error");
                }
            });
            flag = false;
        }

        return flag;
    }
};
$(function() {
    $(".star").append("<span style='color:red'>&nbsp;*</span>");
});
$.fn.validate=function(options) {
    var formValidate = new FormValidate(this, options);
    return formValidate.validate();
};
$.fn.submit=function(fn){
  if(this.validate()) {
      this[0].submit();
  }else{
      fn();
  }
}
$.fn.myAjaxSubmit= $.fn.ajaxSubmit;
$.fn.ajaxSubmit=function(options,repeat) {
    if(this.attr("posted")) {
        options.repeat();
    }else{
        if(this.validate()) {
           if(!repeat) {
               this.attr("posted", "true");
           }
            $(this[0]).myAjaxSubmit(function(data){
                options.success(data);
            });
        }else{
            options.error(this.find(".error"));
        }
    }
};