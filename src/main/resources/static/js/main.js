jQuery(document).ready(function(){new jBoxPlugin("Confirm",{confirmButton:"Submit",cancelButton:"Cancel",confirm:null,cancel:null,closeOnConfirm:!0,target:window,addClass:"jBox-Modal",fixed:!0,attach:"[data-confirm]",getContent:"data-confirm",content:"Do you really want to do this?",minWidth:360,maxWidth:500,blockScroll:!0,closeOnEsc:!0,closeOnClick:!1,closeButton:!1,overlay:!0,animation:"zoomIn",preventDefault:!0,_onAttach:function(t){if(!this.options.confirm){var o=t.attr("onclick")?t.attr("onclick"):t.attr("href")?t.attr("target")?'window.open("'+t.attr("href")+'", "'+t.attr("target")+'");':'window.location.href = "'+t.attr("href")+'";':"";t.prop("onclick",null).data("jBox-Confirm-submit",o)}},_onCreated:function(){this.footer=jQuery('<div class="jBox-Confirm-footer"/>'),jQuery('<div class="jBox-Confirm-button jBox-Confirm-button-cancel"/>').html(this.options.cancelButton).click(function(){this.options.cancel&&this.options.cancel(),this.close()}.bind(this)).appendTo(this.footer),this.submitButton=jQuery('<div class="jBox-Confirm-button jBox-Confirm-button-submit"/>').html(this.options.confirmButton).appendTo(this.footer),this.footer.appendTo(this.container)},_onOpen:function(){this.submitButton.off("click.jBox-Confirm"+this.id).on("click.jBox-Confirm"+this.id,function(){this.options.confirm?this.options.confirm():eval(this.source.data("jBox-Confirm-submit")),this.options.closeOnConfirm&&this.close()}.bind(this))}})});function jBox(t,i){return this.options={id:null,width:"auto",height:"auto",minWidth:null,minHeight:null,maxWidth:null,maxHeight:null,responsiveWidth:!0,responsiveHeight:!0,responsiveMinWidth:100,responsiveMinHeight:100,attach:null,trigger:"click",preventDefault:!1,title:null,content:null,getTitle:null,getContent:null,isolateScroll:!0,ajax:{url:null,data:"",reload:!1,getURL:"data-url",getData:"data-ajax",setContent:!0,spinner:!0,spinnerDelay:300,spinnerReposition:!0},target:null,position:{x:"center",y:"center"},outside:null,offset:0,attributes:{x:"left",y:"top"},fixed:!1,adjustPosition:!0,adjustTracker:!1,adjustDistance:5,reposition:!0,repositionOnOpen:!0,repositionOnContent:!0,pointer:!1,pointTo:"target",fade:180,animation:null,theme:"Default",addClass:null,overlay:!1,zIndex:1e4,delayOpen:0,delayClose:0,closeOnEsc:!1,closeOnClick:!1,closeOnMouseleave:!1,closeButton:!1,appendTo:jQuery("body"),createOnInit:!1,blockScroll:!1,draggable:!1,dragOver:!0,autoClose:!1,preloadAudio:!0,audio:null,volume:100,onInit:null,onAttach:null,onPosition:null,onCreated:null,onOpen:null,onClose:null,onCloseComplete:null},this._pluginOptions={Tooltip:{getContent:"title",trigger:"mouseenter",position:{x:"center",y:"top"},outside:"y",pointer:!0},Mouse:{responsiveWidth:!1,responsiveHeight:!1,adjustPosition:"flip",target:"mouse",trigger:"mouseenter",position:{x:"right",y:"bottom"},outside:"xy",offset:5},Modal:{target:jQuery(window),fixed:!0,blockScroll:!0,closeOnEsc:!0,closeOnClick:"overlay",closeButton:"title",overlay:!0,animation:"zoomIn"}},this.options=jQuery.extend(!0,this.options,this._pluginOptions[t]?this._pluginOptions[t]:jBox._pluginOptions[t],i),"string"==jQuery.type(t)&&(this.type=t),this._fireEvent=function(t,i){this.options["_"+t]&&this.options["_"+t].bind(this)(i),this.options[t]&&this.options[t].bind(this)(i)},null===this.options.id&&(this.options.id="jBox"+jBox._getUniqueID()),this.id=this.options.id,("center"==this.options.position.x&&"x"==this.options.outside||"center"==this.options.position.y&&"y"==this.options.outside)&&(this.options.outside=null),"target"==this.options.pointTo&&(!this.options.outside||"xy"==this.options.outside)&&(this.options.pointer=!1),"object"!=jQuery.type(this.options.offset)?this.options.offset={x:this.options.offset,y:this.options.offset}:this.options.offset=jQuery.extend({x:0,y:0},this.options.offset),"object"!=jQuery.type(this.options.adjustDistance)?this.options.adjustDistance={top:this.options.adjustDistance,right:this.options.adjustDistance,bottom:this.options.adjustDistance,left:this.options.adjustDistance}:this.options.adjustDistance=jQuery.extend({top:5,left:5,right:5,bottom:5},this.options.adjustDistance),this.outside=!(!this.options.outside||"xy"==this.options.outside)&&this.options.position[this.options.outside],this.align=this.outside?this.outside:"center"!=this.options.position.y&&"number"!=jQuery.type(this.options.position.y)?this.options.position.x:"center"!=this.options.position.x&&"number"!=jQuery.type(this.options.position.x)?this.options.position.y:this.options.attributes.x,this._getOpp=function(t){return{left:"right",right:"left",top:"bottom",bottom:"top",x:"y",y:"x"}[t]},this._getXY=function(t){return{left:"x",right:"x",top:"y",bottom:"y",center:"x"}[t]},this._getTL=function(t){return{left:"left",right:"left",top:"top",bottom:"top",center:"left",x:"left",y:"top"}[t]},this._getInt=function(t,i){return"auto"==t?"auto":t&&"string"==jQuery.type(t)&&"%"==t.slice(-1)?jQuery(window)["height"==i?"innerHeight":"innerWidth"]()*parseInt(t.replace("%",""))/100:t},this._createSVG=function(t,i){var s=document.createElementNS("http://www.w3.org/2000/svg",t);return jQuery.each(i,function(t,i){s.setAttribute(i[0],i[1]||"")}),s},this._isolateScroll=function(t){t&&t.length&&t.on("DOMMouseScroll.jBoxIsolateScroll mousewheel.jBoxIsolateScroll",function(i){var s=i.wheelDelta||i.originalEvent&&i.originalEvent.wheelDelta||-i.detail,o=this.scrollTop+t.outerHeight()-this.scrollHeight>=0,e=this.scrollTop<=0;(s<0&&o||s>0&&e)&&i.preventDefault()})},this._setTitleWidth=function(){if(!this.titleContainer||"auto"==this.content[0].style.width&&!this.content[0].style.maxWidth)return null;if("none"==this.wrapper.css("display")){this.wrapper.css("display","block");var t=this.content.outerWidth();this.wrapper.css("display","none")}else var t=this.content.outerWidth();this.titleContainer.css({maxWidth:Math.max(t,parseInt(this.content[0].style.maxWidth))||null})},this._draggable=function(){if(!this.options.draggable)return!1;var t="title"==this.options.draggable?this.titleContainer:this.options.draggable instanceof jQuery?this.options.draggable:"string"==jQuery.type(this.options.draggable)?jQuery(this.options.draggable):this.wrapper;return!(!(t&&t instanceof jQuery&&t.length)||t.data("jBox-draggable"))&&(t.addClass("jBox-draggable").data("jBox-draggable",!0).on("mousedown",function(t){if(2!=t.button&&!jQuery(t.target).hasClass("jBox-noDrag")&&!jQuery(t.target).parents(".jBox-noDrag").length){this.options.dragOver&&this.wrapper.css("zIndex")<=jBox.zIndexMax&&(jBox.zIndexMax+=1,this.wrapper.css("zIndex",jBox.zIndexMax));var i=this.wrapper.outerHeight(),s=this.wrapper.outerWidth(),o=this.wrapper.offset().top+i-t.pageY,e=this.wrapper.offset().left+s-t.pageX;jQuery(document).on("mousemove.jBox-draggable-"+this.id,function(t){this.wrapper.offset({top:t.pageY+o-i,left:t.pageX+e-s})}.bind(this)),t.preventDefault()}}.bind(this)).on("mouseup",function(){jQuery(document).off("mousemove.jBox-draggable-"+this.id)}.bind(this)),jBox.zIndexMax=jBox.zIndexMax?Math.max(jBox.zIndexMax,this.options.zIndex):this.options.zIndex,this)},this._create=function(){if(!this.wrapper){if(this.wrapper=jQuery("<div/>",{id:this.id,class:"jBox-wrapper"+(this.type?" jBox-"+this.type:"")+(this.options.theme?" jBox-"+this.options.theme:"")+(this.options.addClass?" "+this.options.addClass:"")}).css({position:this.options.fixed?"fixed":"absolute",display:"none",opacity:0,zIndex:this.options.zIndex}).data("jBox",this),this.options.closeOnMouseleave&&this.wrapper.on("mouseleave",function(t){!this.source||!(t.relatedTarget==this.source[0]||jQuery.inArray(this.source[0],jQuery(t.relatedTarget).parents("*"))!==-1)&&this.close()}.bind(this)),"box"==this.options.closeOnClick&&this.wrapper.on("touchend click",function(){this.close({ignoreDelay:!0})}.bind(this)),this.container=jQuery('<div class="jBox-container"/>').appendTo(this.wrapper),this.content=jQuery('<div class="jBox-content"/>').appendTo(this.container),this.options.footer&&(this.footer=jQuery('<div class="jBox-footer"/>').append(this.options.footer).appendTo(this.container)),this.options.isolateScroll&&this._isolateScroll(this.content),this.options.closeButton){var t=this._createSVG("svg",[["viewBox","0 0 24 24"]]);t.appendChild(this._createSVG("path",[["d","M22.2,4c0,0,0.5,0.6,0,1.1l-6.8,6.8l6.9,6.9c0.5,0.5,0,1.1,0,1.1L20,22.3c0,0-0.6,0.5-1.1,0L12,15.4l-6.9,6.9c-0.5,0.5-1.1,0-1.1,0L1.7,20c0,0-0.5-0.6,0-1.1L8.6,12L1.7,5.1C1.2,4.6,1.7,4,1.7,4L4,1.7c0,0,0.6-0.5,1.1,0L12,8.5l6.8-6.8c0.5-0.5,1.1,0,1.1,0L22.2,4z"]])),this.closeButton=jQuery('<div class="jBox-closeButton jBox-noDrag"/>').on("touchend click",function(t){this.close({ignoreDelay:!0})}.bind(this)).append(t),"box"!=this.options.closeButton&&(this.options.closeButton!==!0||this.options.overlay||this.options.title)||(this.wrapper.addClass("jBox-closeButton-box"),this.closeButton.appendTo(this.container))}if(this.wrapper.appendTo(this.options.appendTo),this.wrapper.find(".jBox-closeButton").length&&jQuery.each(["top","right","bottom","left"],function(t,i){this.wrapper.find(".jBox-closeButton").css(i)&&"auto"!=this.wrapper.find(".jBox-closeButton").css(i)&&(this.options.adjustDistance[i]=Math.max(this.options.adjustDistance[i],this.options.adjustDistance[i]+((parseInt(this.wrapper.find(".jBox-closeButton").css(i))||0)+(parseInt(this.container.css("border-"+i+"-width"))||0))*-1))}.bind(this)),this.options.pointer){if(this.pointer={position:"target"!=this.options.pointTo?this.options.pointTo:this._getOpp(this.outside),xy:"target"!=this.options.pointTo?this._getXY(this.options.pointTo):this._getXY(this.outside),align:"center",offset:0},this.pointer.element=jQuery('<div class="jBox-pointer jBox-pointer-'+this.pointer.position+'"/>').appendTo(this.wrapper),this.pointer.dimensions={x:this.pointer.element.outerWidth(),y:this.pointer.element.outerHeight()},"string"==jQuery.type(this.options.pointer)){var i=this.options.pointer.split(":");i[0]&&(this.pointer.align=i[0]),i[1]&&(this.pointer.offset=parseInt(i[1]))}this.pointer.alignAttribute="x"==this.pointer.xy?"bottom"==this.pointer.align?"bottom":"top":"right"==this.pointer.align?"right":"left",this.wrapper.css("padding-"+this.pointer.position,this.pointer.dimensions[this.pointer.xy]),this.pointer.element.css(this.pointer.alignAttribute,"center"==this.pointer.align?"50%":0).css("margin-"+this.pointer.alignAttribute,this.pointer.offset),this.pointer.margin={},this.pointer.margin["margin-"+this.pointer.alignAttribute]=this.pointer.offset,"center"==this.pointer.align&&this.pointer.element.css("transform","translate("+("y"==this.pointer.xy?this.pointer.dimensions.x*-.5+"px":0)+", "+("x"==this.pointer.xy?this.pointer.dimensions.y*-.5+"px":0)+")"),this.pointer.element.css("x"==this.pointer.xy?"width":"height",parseInt(this.pointer.dimensions[this.pointer.xy])+parseInt(this.container.css("border-"+this.pointer.alignAttribute+"-width"))),this.wrapper.addClass("jBox-pointerPosition-"+this.pointer.position)}this.setContent(this.options.content,!0),this.setTitle(this.options.title,!0),this.options.draggable&&this._draggable(),this._fireEvent("onCreated")}},this.options.createOnInit&&this._create(),this.options.attach&&this.attach(),this._attachEvents=function(){this.options.closeOnEsc&&jQuery(document).on("keyup.jBox-"+this.id,function(t){27==t.keyCode&&this.close({ignoreDelay:!0})}.bind(this)),this.options.closeOnClick!==!0&&"body"!=this.options.closeOnClick||jQuery(document).on("touchend.jBox-"+this.id+" click.jBox-"+this.id,function(t){this.blockBodyClick||"body"==this.options.closeOnClick&&(t.target==this.wrapper[0]||this.wrapper.has(t.target).length)||this.close({ignoreDelay:!0})}.bind(this)),(this.options.adjustPosition||this.options.reposition)&&!this.fixed&&this.outside&&(this.options.adjustTracker&&jQuery(window).on("scroll.jBox-"+this.id,function(t){this.position()}.bind(this)),(this.options.adjustPosition||this.options.reposition)&&jQuery(window).on("resize.jBox-"+this.id,function(t){this.position()}.bind(this))),"mouse"==this.options.target&&jQuery("body").on("mousemove.jBox-"+this.id,function(t){this.position({mouseTarget:{top:t.pageY,left:t.pageX}})}.bind(this))},this._detachEvents=function(){this.options.closeOnEsc&&jQuery(document).off("keyup.jBox-"+this.id),(this.options.closeOnClick===!0||"body"==this.options.closeOnClick)&&jQuery(document).off("touchend.jBox-"+this.id+" click.jBox-"+this.id),this.options.adjustTracker&&jQuery(window).off("scroll.jBox-"+this.id),(this.options.adjustPosition||this.options.reposition)&&jQuery(window).off("resize.jBox-"+this.id),"mouse"==this.options.target&&jQuery("body").off("mousemove.jBox-"+this.id)},this._showOverlay=function(){this.overlay||(this.overlay=jQuery('<div id="'+this.id+'-overlay"/>').addClass("jBox-overlay"+(this.type?" jBox-overlay-"+this.type:"")).css({display:"none",opacity:0,zIndex:this.options.zIndex-1}).appendTo(this.options.appendTo),("overlay"==this.options.closeButton||this.options.closeButton===!0)&&this.overlay.append(this.closeButton),"overlay"==this.options.closeOnClick&&this.overlay.on("touchend click",function(){this.close({ignoreDelay:!0})}.bind(this)),jQuery("#"+this.id+"-overlay .jBox-closeButton").length&&(this.options.adjustDistance.top=Math.max(jQuery("#"+this.id+"-overlay .jBox-closeButton").outerHeight(),this.options.adjustDistance.top))),"block"!=this.overlay.css("display")&&(this.options.fade?this.overlay.stop()&&this.overlay.animate({opacity:1},{queue:!1,duration:this.options.fade,start:function(){this.overlay.css({display:"block"})}.bind(this)}):this.overlay.css({display:"block",opacity:1}))},this._hideOverlay=function(){this.overlay&&(this.options.fade?this.overlay.stop()&&this.overlay.animate({opacity:0},{queue:!1,duration:this.options.fade,complete:function(){this.overlay.css({display:"none"})}.bind(this)}):this.overlay.css({display:"none",opacity:0}))},this._exposeDimensions=function(){this.wrapper.css({top:-1e4,left:-1e4,right:"auto",bottom:"auto"});var t={x:this.wrapper.outerWidth(),y:this.wrapper.outerHeight()};return this.wrapper.css({top:"auto",left:"auto"}),t},this._generateAnimationCSS=function(){if("object"!=jQuery.type(this.options.animation)&&(this.options.animation={pulse:{open:"pulse",close:"zoomOut"},zoomIn:{open:"zoomIn",close:"zoomIn"},zoomOut:{open:"zoomOut",close:"zoomOut"},move:{open:"move",close:"move"},slide:{open:"slide",close:"slide"},flip:{open:"flip",close:"flip"},tada:{open:"tada",close:"zoomOut"}}[this.options.animation]),!this.options.animation)return null;this.options.animation.open&&(this.options.animation.open=this.options.animation.open.split(":")),this.options.animation.close&&(this.options.animation.close=this.options.animation.close.split(":")),this.options.animation.openDirection=this.options.animation.open[1]?this.options.animation.open[1]:null,this.options.animation.closeDirection=this.options.animation.close[1]?this.options.animation.close[1]:null,this.options.animation.open&&(this.options.animation.open=this.options.animation.open[0]),this.options.animation.close&&(this.options.animation.close=this.options.animation.close[0]),this.options.animation.open&&(this.options.animation.open+="Open"),this.options.animation.close&&(this.options.animation.close+="Close");var t={pulse:{duration:350,css:[["0%","scale(1)"],["50%","scale(1.1)"],["100%","scale(1)"]]},zoomInOpen:{duration:this.options.fade||180,css:[["0%","scale(0.9)"],["100%","scale(1)"]]},zoomInClose:{duration:this.options.fade||180,css:[["0%","scale(1)"],["100%","scale(0.9)"]]},zoomOutOpen:{duration:this.options.fade||180,css:[["0%","scale(1.1)"],["100%","scale(1)"]]},zoomOutClose:{duration:this.options.fade||180,css:[["0%","scale(1)"],["100%","scale(1.1)"]]},moveOpen:{duration:this.options.fade||180,positions:{top:{"0%":-12},right:{"0%":12},bottom:{"0%":12},left:{"0%":-12}},css:[["0%","translate%XY(%Vpx)"],["100%","translate%XY(0px)"]]},moveClose:{duration:this.options.fade||180,timing:"ease-in",positions:{top:{"100%":-12},right:{"100%":12},bottom:{"100%":12},left:{"100%":-12}},css:[["0%","translate%XY(0px)"],["100%","translate%XY(%Vpx)"]]},slideOpen:{duration:400,positions:{top:{"0%":-400},right:{"0%":400},bottom:{"0%":400},left:{"0%":-400}},css:[["0%","translate%XY(%Vpx)"],["100%","translate%XY(0px)"]]},slideClose:{duration:400,timing:"ease-in",positions:{top:{"100%":-400},right:{"100%":400},bottom:{"100%":400},left:{"100%":-400}},css:[["0%","translate%XY(0px)"],["100%","translate%XY(%Vpx)"]]},flipOpen:{duration:600,css:[["0%","perspective(400px) rotateX(90deg)"],["40%","perspective(400px) rotateX(-15deg)"],["70%","perspective(400px) rotateX(15deg)"],["100%","perspective(400px) rotateX(0deg)"]]},flipClose:{duration:this.options.fade||300,css:[["0%","perspective(400px) rotateX(0deg)"],["100%","perspective(400px) rotateX(90deg)"]]},tada:{duration:800,css:[["0%","scale(1)"],["10%, 20%","scale(0.9) rotate(-3deg)"],["30%, 50%, 70%, 90%","scale(1.1) rotate(3deg)"],["40%, 60%, 80%","scale(1.1) rotate(-3deg)"],["100%","scale(1) rotate(0)"]]}};jQuery.each(["pulse","tada"],function(i,s){t[s+"Open"]=t[s+"Close"]=t[s]});var i=function(i,s){return keyframe_css="@keyframes jBox-"+this.id+"-animation-"+this.options.animation[i]+"-"+i+(s?"-"+s:"")+" {",jQuery.each(t[this.options.animation[i]].css,function(o,e){var n=s?e[1].replace("%XY",this._getXY(s).toUpperCase()):e[1];t[this.options.animation[i]].positions&&(n=n.replace("%V",t[this.options.animation[i]].positions[s][e[0]])),keyframe_css+=e[0]+" {transform:"+n+";}"}.bind(this)),keyframe_css+="}",keyframe_css+=".jBox-"+this.id+"-animation-"+this.options.animation[i]+"-"+i+(s?"-"+s:"")+" {",keyframe_css+="animation-duration: "+t[this.options.animation[i]].duration+"ms;",keyframe_css+="animation-name: jBox-"+this.id+"-animation-"+this.options.animation[i]+"-"+i+(s?"-"+s:"")+";",keyframe_css+=t[this.options.animation[i]].timing?"animation-timing-function: "+t[this.options.animation[i]].timing+";":"",keyframe_css+="}",keyframe_css}.bind(this);this._animationCSS="",jQuery.each(["open","close"],function(s,o){return this.options.animation[o]&&t[this.options.animation[o]]&&("close"!=o||this.options.fade)?void(t[this.options.animation[o]].positions?jQuery.each(["top","right","bottom","left"],function(t,s){this._animationCSS+=i(o,s)}.bind(this)):this._animationCSS+=i(o)):""}.bind(this))},this.options.animation&&this._generateAnimationCSS(),this._blockBodyClick=function(){this.blockBodyClick=!0,setTimeout(function(){this.blockBodyClick=!1}.bind(this),10)},this._animate=function(t){if(!t&&(t=this.isOpen?"open":"close"),!this.options.fade&&"close"==t)return null;var i=this.options.animation[t+"Direction"]||("center"!=this.align?this.align:this.options.attributes.x);this.flipped&&this._getXY(i)==this._getXY(this.align)&&(i=this._getOpp(i));var s="jBox-"+this.id+"-animation-"+this.options.animation[t]+"-"+t+" jBox-"+this.id+"-animation-"+this.options.animation[t]+"-"+t+"-"+i;this.wrapper.addClass(s);var o=1e3*parseFloat(this.wrapper.css("animation-duration"));"close"==t&&(o=Math.min(o,this.options.fade)),setTimeout(function(){this.wrapper.removeClass(s)}.bind(this),o)},this._abortAnimation=function(){var t=this.wrapper.attr("class").split(" ").filter(function(t){return 0!==t.lastIndexOf("jBox-"+this.id+"-animation",0)}.bind(this));this.wrapper.attr("class",t.join(" "))},(this.options.responsiveWidth||this.options.responsiveHeight)&&jQuery(window).on("resize.responsivejBox-"+this.id,function(t){this.isOpen&&this.position()}.bind(this)),"string"===jQuery.type(this.options.preloadAudio)&&(this.options.preloadAudio=[this.options.preloadAudio]),"string"===jQuery.type(this.options.audio)&&(this.options.audio={open:this.options.audio}),"number"===jQuery.type(this.options.volume)&&(this.options.volume={open:this.options.volume,close:this.options.volume}),this.options.preloadAudio===!0&&this.options.audio&&(this.options.preloadAudio=[],jQuery.each(this.options.audio,function(t,i){this.options.preloadAudio.push(i+".mp3"),this.options.preloadAudio.push(i+".ogg")}.bind(this))),this.options.preloadAudio.length&&jQuery.each(this.options.preloadAudio,function(t,i){var s=new Audio;s.src=i,s.preload="auto"}),this._fireEvent("onInit"),this}function jBoxPlugin(t,i){jBox._pluginOptions[t]=i}jBox.prototype.attach=function(t,i){return!t&&(t=this.options.attach),"string"==jQuery.type(t)&&(t=jQuery(t)),!i&&(i=this.options.trigger),t&&t.length&&jQuery.each(t,function(t,s){s=jQuery(s),s.data("jBox-attached-"+this.id)||("title"==this.options.getContent&&void 0!=s.attr("title")&&s.data("jBox-getContent",s.attr("title")).removeAttr("title"),this.attachedElements||(this.attachedElements=[]),this.attachedElements.push(s[0]),s.on(i+".jBox-attach-"+this.id,function(t){if(this.timer&&clearTimeout(this.timer),"mouseenter"!=i||!this.isOpen||this.source[0]!=s[0]){if(this.isOpen&&this.source&&this.source[0]!=s[0])var o=!0;this.source=s,!this.options.target&&(this.target=s),"click"==i&&this.options.preventDefault&&t.preventDefault(),this["click"!=i||o?"open":"toggle"]()}}.bind(this)),"mouseenter"==this.options.trigger&&s.on("mouseleave",function(t){return this.wrapper?void(this.options.closeOnMouseleave&&(t.relatedTarget==this.wrapper[0]||jQuery(t.relatedTarget).parents("#"+this.id).length)||this.close()):null}.bind(this)),s.data("jBox-attached-"+this.id,i),this._fireEvent("onAttach",s))}.bind(this)),this},jBox.prototype.detach=function(t){return!t&&(t=this.attachedElements||[]),t&&t.length&&jQuery.each(t,function(t,i){i=jQuery(i),i.data("jBox-attached-"+this.id)&&(i.off(i.data("jBox-attached-"+this.id)+".jBox-attach-"+this.id),i.data("jBox-attached-"+this.id,null)),this.attachedElements=jQuery.grep(this.attachedElements,function(t){return t!=i[0]})}.bind(this)),this},jBox.prototype.setTitle=function(t,i){if(null==t||void 0==t)return this;!this.wrapper&&this._create();var s=this.wrapper.outerHeight(),o=this.wrapper.outerWidth();return this.title||(this.titleContainer=jQuery('<div class="jBox-title"/>'),this.title=jQuery("<div/>").appendTo(this.titleContainer),this.wrapper.addClass("jBox-hasTitle"),("title"==this.options.closeButton||this.options.closeButton===!0&&!this.options.overlay)&&(this.wrapper.addClass("jBox-closeButton-title"),this.closeButton.appendTo(this.titleContainer)),this.titleContainer.insertBefore(this.content),this._setTitleWidth()),this.title.html(t),o!=this.wrapper.outerWidth()&&this._setTitleWidth(),this.options.draggable&&this._draggable(),!i&&this.options.repositionOnContent&&(s!=this.wrapper.outerHeight()||o!=this.wrapper.outerWidth())&&this.position(),this},jBox.prototype.setContent=function(t,i){if(null==t||void 0==t)return this;!this.wrapper&&this._create();var s=this.wrapper.outerHeight(),o=this.wrapper.outerWidth();switch(this.content.children("[data-jbox-content-appended]").appendTo("body").css({display:"none"}),jQuery.type(t)){case"string":this.content.html(t);break;case"object":this.content.html(""),t.attr("data-jbox-content-appended",1).appendTo(this.content).css({display:"block"})}return o!=this.wrapper.outerWidth()&&this._setTitleWidth(),this.options.draggable&&this._draggable(),!i&&this.options.repositionOnContent&&(s!=this.wrapper.outerHeight()||o!=this.wrapper.outerWidth())&&this.position(),this},jBox.prototype.setDimensions=function(t,i,s){!this.wrapper&&this._create(),void 0==i&&(i="auto"),this.content.css(t,this._getInt(i)),"width"==t&&this._setTitleWidth(),(void 0==s||s)&&this.position()},jBox.prototype.setWidth=function(t,i){this.setDimensions("width",t,i)},jBox.prototype.setHeight=function(t,i){this.setDimensions("height",t,i)},jBox.prototype.position=function(t){if(!t&&(t={}),t=jQuery.extend(!0,this.options,t),this.target=t.target||this.target||jQuery(window),!(this.target instanceof jQuery||"mouse"==this.target)&&(this.target=jQuery(this.target)),!this.target.length)return this;this.content.css({width:this._getInt(t.width,"width"),height:this._getInt(t.height,"height"),minWidth:this._getInt(t.minWidth,"width"),minHeight:this._getInt(t.minHeight,"height"),maxWidth:this._getInt(t.maxWidth,"width"),maxHeight:this._getInt(t.maxHeight,"height")}),this._setTitleWidth();var i=this._exposeDimensions();"mouse"!=this.target&&!this.target.data("jBox-"+this.id+"-fixed")&&this.target.data("jBox-"+this.id+"-fixed",this.target[0]!=jQuery(window)[0]&&("fixed"==this.target.css("position")||this.target.parents().filter(function(){return"fixed"==jQuery(this).css("position")}).length>0)?"fixed":"static");var s={x:jQuery(window).outerWidth(),y:jQuery(window).outerHeight(),top:t.fixed&&this.target.data("jBox-"+this.id+"-fixed")?0:jQuery(window).scrollTop(),left:t.fixed&&this.target.data("jBox-"+this.id+"-fixed")?0:jQuery(window).scrollLeft()};s.bottom=s.top+s.y,s.right=s.left+s.x;try{var o=this.target.offset()}catch(t){var o={top:0,left:0}}"mouse"!=this.target&&"fixed"==this.target.data("jBox-"+this.id+"-fixed")&&t.fixed&&(o.top=o.top-jQuery(window).scrollTop(),o.left=o.left-jQuery(window).scrollLeft());var e={x:"mouse"==this.target?12:this.target.outerWidth(),y:"mouse"==this.target?20:this.target.outerHeight(),top:"mouse"==this.target&&t.mouseTarget?t.mouseTarget.top:o?o.top:0,left:"mouse"==this.target&&t.mouseTarget?t.mouseTarget.left:o?o.left:0},n=t.outside&&!("center"==t.position.x&&"center"==t.position.y),a={x:s.x-t.adjustDistance.left-t.adjustDistance.right,y:s.y-t.adjustDistance.top-t.adjustDistance.bottom,left:n?e.left-jQuery(window).scrollLeft()-t.adjustDistance.left:0,right:n?s.x-e.left+jQuery(window).scrollLeft()-e.x-t.adjustDistance.right:0,top:n?e.top-jQuery(window).scrollTop()-this.options.adjustDistance.top:0,bottom:n?s.y-e.top+jQuery(window).scrollTop()-e.y-t.adjustDistance.bottom:0},h={x:"x"!=t.outside&&"xy"!=t.outside||"number"==jQuery.type(t.position.x)?null:t.position.x,y:"y"!=t.outside&&"xy"!=t.outside||"number"==jQuery.type(t.position.y)?null:t.position.y},r={x:!1,y:!1};if(h.x&&i.x>a[h.x]&&a[this._getOpp(h.x)]>a[h.x]&&(h.x=this._getOpp(h.x))&&(r.x=!0),h.y&&i.y>a[h.y]&&a[this._getOpp(h.y)]>a[h.y]&&(h.y=this._getOpp(h.y))&&(r.y=!0),t.responsiveWidth||t.responsiveHeight){var p=function(){if(t.responsiveWidth&&i.x>a[h.x||"x"]){var s=a[h.x||"x"]-(this.pointer&&n&&"x"==t.outside?this.pointer.dimensions.x:0)-parseInt(this.container.css("border-left-width"))-parseInt(this.container.css("border-right-width"));this.content.css({width:s>this.options.responsiveMinWidth?s:null,minWidth:s<parseInt(this.content.css("minWidth"))?0:null}),this._setTitleWidth()}i=this._exposeDimensions()}.bind(this);t.responsiveWidth&&p(),t.responsiveWidth&&!r.y&&h.y&&i.y>a[h.y]&&a[this._getOpp(h.y)]>a[h.y]&&(h.y=this._getOpp(h.y))&&(r.y=!0);var l=function(){if(t.responsiveHeight&&i.y>a[h.y||"y"]){var s=function(){if(!this.titleContainer&&!this.footer)return 0;if("none"==this.wrapper.css("display")){this.wrapper.css("display","block");var t=(this.titleContainer?this.titleContainer.outerHeight():0)+(this.footer?this.footer.outerHeight():0);this.wrapper.css("display","none")}else var t=(this.titleContainer?this.titleContainer.outerHeight():0)+(this.footer?this.footer.outerHeight():0);return t||0}.bind(this),o=a[h.y||"y"]-(this.pointer&&n&&"y"==t.outside?this.pointer.dimensions.y:0)-s()-parseInt(this.container.css("border-top-width"))-parseInt(this.container.css("border-bottom-width"));this.content.css({height:o>this.options.responsiveMinHeight?o:null}),this._setTitleWidth()}i=this._exposeDimensions()}.bind(this);t.responsiveHeight&&l(),t.responsiveHeight&&!r.x&&h.x&&i.x>a[h.x]&&a[this._getOpp(h.x)]>a[h.x]&&(h.x=this._getOpp(h.x))&&(r.x=!0),t.adjustPosition&&"move"!=t.adjustPosition&&(r.x&&p(),r.y&&l())}var d={},u=function(s){if("number"==jQuery.type(t.position[s]))return void(d[t.attributes[s]]=t.position[s]);var o=t.attributes[s]="x"==s?"left":"top";return d[o]=e[o],"center"==t.position[s]?(d[o]+=Math.ceil((e[s]-i[s])/2),void("mouse"!=this.target&&this.target[0]&&this.target[0]==jQuery(window)[0]&&(d[o]+=.5*(t.adjustDistance[o]-t.adjustDistance[this._getOpp(o)])))):(o!=t.position[s]&&(d[o]+=e[s]-i[s]),void((t.outside==s||"xy"==t.outside)&&(d[o]+=i[s]*(o!=t.position[s]?1:-1))))}.bind(this);if(u("x"),u("y"),this.pointer&&"target"==t.pointTo&&"number"!=jQuery.type(t.position.x)&&"number"!=jQuery.type(t.position.y)){var c=0;switch(this.pointer.align){case"center":"center"!=t.position[this._getOpp(t.outside)]&&(c+=i[this._getOpp(t.outside)]/2);break;default:switch(t.position[this._getOpp(t.outside)]){case"center":c+=(i[this._getOpp(t.outside)]/2-this.pointer.dimensions[this._getOpp(t.outside)]/2)*(this.pointer.align==this._getTL(this.pointer.align)?1:-1);break;default:c+=this.pointer.align!=t.position[this._getOpp(t.outside)]?this.dimensions[this._getOpp(t.outside)]*(jQuery.inArray(this.pointer.align,["top","left"])!==-1?1:-1)+this.pointer.dimensions[this._getOpp(t.outside)]/2*(jQuery.inArray(this.pointer.align,["top","left"])!==-1?-1:1):this.pointer.dimensions[this._getOpp(t.outside)]/2*(jQuery.inArray(this.pointer.align,["top","left"])!==-1?1:-1)}}c*=t.position[this._getOpp(t.outside)]==this.pointer.alignAttribute?-1:1,c+=this.pointer.offset*(this.pointer.align==this._getOpp(this._getTL(this.pointer.align))?1:-1),d[this._getTL(this._getOpp(this.pointer.xy))]+=c}if(d[t.attributes.x]+=t.offset.x,d[t.attributes.y]+=t.offset.y,this.wrapper.css(d),t.adjustPosition){this.positionAdjusted&&(this.pointer&&this.wrapper.css("padding",0).css("padding-"+this._getOpp(this.outside),this.pointer.dimensions[this._getXY(this.outside)]).removeClass("jBox-pointerPosition-"+this._getOpp(this.pointer.position)).addClass("jBox-pointerPosition-"+this.pointer.position),this.pointer&&this.pointer.element.attr("class","jBox-pointer jBox-pointer-"+this._getOpp(this.outside)).css(this.pointer.margin),this.positionAdjusted=!1,this.flipped=!1);var g=s.top>d.top-(t.adjustDistance.top||0),m=s.right<d.left+i.x+(t.adjustDistance.right||0),f=s.bottom<d.top+i.y+(t.adjustDistance.bottom||0),y=s.left>d.left-(t.adjustDistance.left||0),x=y?"left":m?"right":null,j=g?"top":f?"bottom":null,b=x||j;if(b){var v=function(s){this.wrapper.css(this._getTL(s),d[this._getTL(s)]+(i[this._getXY(s)]+t.offset[this._getXY(s)]*("top"==s||"left"==s?-2:2)+e[this._getXY(s)])*("top"==s||"left"==s?1:-1)),this.pointer&&this.wrapper.removeClass("jBox-pointerPosition-"+this.pointer.position).addClass("jBox-pointerPosition-"+this._getOpp(this.pointer.position)).css("padding",0).css("padding-"+s,this.pointer.dimensions[this._getXY(s)]),this.pointer&&this.pointer.element.attr("class","jBox-pointer jBox-pointer-"+s),this.positionAdjusted=!0,this.flipped=!0}.bind(this);r.x&&v(this.options.position.x),r.y&&v(this.options.position.y);var B="x"==this._getXY(this.outside)?j:x;if(this.pointer&&"target"==t.pointTo&&"flip"!=t.adjustPosition&&this._getXY(B)==this._getOpp(this._getXY(this.outside))){if("center"==this.pointer.align)var _=i[this._getXY(B)]/2-this.pointer.dimensions[this._getOpp(this.pointer.xy)]/2-parseInt(this.pointer.element.css("margin-"+this.pointer.alignAttribute))*(B!=this._getTL(B)?-1:1);else var _=B==this.pointer.alignAttribute?parseInt(this.pointer.element.css("margin-"+this.pointer.alignAttribute)):i[this._getXY(B)]-parseInt(this.pointer.element.css("margin-"+this.pointer.alignAttribute))-this.pointer.dimensions[this._getXY(B)];spaceDiff=B==this._getTL(B)?s[this._getTL(B)]-d[this._getTL(B)]+t.adjustDistance[B]:(s[this._getOpp(this._getTL(B))]-d[this._getTL(B)]-t.adjustDistance[B]-i[this._getXY(B)])*-1,B==this._getOpp(this._getTL(B))&&d[this._getTL(B)]-spaceDiff<s[this._getTL(B)]+t.adjustDistance[this._getTL(B)]&&(spaceDiff-=s[this._getTL(B)]+t.adjustDistance[this._getTL(B)]-(this.pos[this._getTL(B)]-spaceDiff)),spaceDiff=Math.min(spaceDiff,_),spaceDiff<=_&&spaceDiff>0&&(this.pointer.element.css("margin-"+this.pointer.alignAttribute,parseInt(this.pointer.element.css("margin-"+this.pointer.alignAttribute))-spaceDiff*(B!=this.pointer.alignAttribute?-1:1)),this.wrapper.css(this._getTL(B),d[this._getTL(B)]+spaceDiff*(B!=this._getTL(B)?-1:1)),this.positionAdjusted=!0)}}}return this._fireEvent("onPosition"),this},jBox.prototype.open=function(t){if(!t&&(t={}),this.isDestroyed)return!1;if(!this.wrapper&&this._create(),!this._styles&&(this._styles=jQuery("<style/>").append(this._animationCSS).appendTo(jQuery("head"))),this.timer&&clearTimeout(this.timer),this._blockBodyClick(),this.isDisabled)return this;var i=function(){this.source&&this.options.getTitle&&(this.source.attr(this.options.getTitle)&&this.setTitle(this.source.attr(this.options.getTitle)),!0),this.source&&this.options.getContent&&(this.source.data("jBox-getContent")?this.setContent(this.source.data("jBox-getContent"),!0):this.source.attr(this.options.getContent)?this.setContent(this.source.attr(this.options.getContent),!0):"html"==this.options.getContent?this.setContent(this.source.html(),!0):null),this._fireEvent("onOpen"),(this.options.ajax&&(this.options.ajax.url||this.source&&this.source.attr(this.options.ajax.getURL))&&(!this.ajaxLoaded||this.options.ajax.reload)||t.ajax&&(t.ajax.url||t.ajax.data))&&("strict"==this.options.ajax.reload||!this.source||!this.source.data("jBox-ajax-data")||t.ajax&&(t.ajax.url||t.ajax.data)?this.ajax(t.ajax||null,!0):this.setContent(this.source.data("jBox-ajax-data"))),(!this.positionedOnOpen||this.options.repositionOnOpen)&&this.position(t)&&(this.positionedOnOpen=!0),this.isClosing&&this._abortAnimation(),this.isOpen||(this.isOpen=!0,this.options.autoClose&&(this.options.delayClose=this.options.autoClose)&&this.close(),this._attachEvents(),this.options.blockScroll&&jQuery("body").addClass("jBox-blockScroll-"+this.id),
this.options.overlay&&this._showOverlay(),this.options.animation&&!this.isClosing&&this._animate("open"),this.options.audio&&this.options.audio.open&&this.audio(this.options.audio.open,this.options.volume.open),this.options.fade?this.wrapper.stop().animate({opacity:1},{queue:!1,duration:this.options.fade,start:function(){this.isOpening=!0,this.wrapper.css({display:"block"})}.bind(this),always:function(){this.isOpening=!1,setTimeout(function(){this.positionOnFadeComplete&&this.position()&&(this.positionOnFadeComplete=!1)}.bind(this),10)}.bind(this)}):(this.wrapper.css({display:"block",opacity:1}),this.positionOnFadeComplete&&this.position()&&(this.positionOnFadeComplete=!1)))}.bind(this);return!this.options.delayOpen||this.isOpen||this.isClosing||t.ignoreDelay?i():this.timer=setTimeout(i,this.options.delayOpen),this},jBox.prototype.close=function(t){if(t||(t={}),this.isDestroyed||this.isClosing)return!1;if(this.timer&&clearTimeout(this.timer),this._blockBodyClick(),this.isDisabled)return this;var i=function(){this._fireEvent("onClose"),this.isOpen&&(this.isOpen=!1,this._detachEvents(),this.options.blockScroll&&jQuery("body").removeClass("jBox-blockScroll-"+this.id),this.options.overlay&&this._hideOverlay(),this.options.animation&&!this.isOpening&&this._animate("close"),this.options.audio&&this.options.audio.close&&this.audio(this.options.audio.close,this.options.volume.close),this.options.fade?this.wrapper.stop().animate({opacity:0},{queue:!1,duration:this.options.fade,start:function(){this.isClosing=!0}.bind(this),complete:function(){this.wrapper.css({display:"none"}),this._fireEvent("onCloseComplete")}.bind(this),always:function(){this.isClosing=!1}.bind(this)}):(this.wrapper.css({display:"none",opacity:0}),this._fireEvent("onCloseComplete")))}.bind(this);return t.ignoreDelay?i():this.timer=setTimeout(i,Math.max(this.options.delayClose,10)),this},jBox.prototype.toggle=function(t){return this[this.isOpen?"close":"open"](t),this},jBox.prototype.disable=function(){return this.isDisabled=!0,this},jBox.prototype.enable=function(){return this.isDisabled=!1,this},jBox.prototype.hide=function(){return this.disable(),this.wrapper&&this.wrapper.css({display:"none"}),this},jBox.prototype.show=function(){return this.enable(),this.wrapper&&this.wrapper.css({display:"block"}),this},jBox.prototype.ajax=function(t,i){t||(t={}),jQuery.each([["getData","data"],["getURL","url"]],function(i,s){this.options.ajax[s[0]]&&!t[s[1]]&&this.source&&void 0!=this.source.attr(this.options.ajax[s[0]])&&(t[s[1]]=this.source.attr(this.options.ajax[s[0]])||"")}.bind(this));var s=jQuery.extend(!0,{},this.options.ajax);this.ajaxRequest&&this.ajaxRequest.abort();var o=t.beforeSend||s.beforeSend||function(){},e=t.complete||s.complete||function(){},n=t.success||s.success||function(){},a=t.error||s.error||function(){},h=jQuery.extend(!0,s,t);return h.beforeSend=function(){this.wrapper.addClass("jBox-loading"),h.spinner&&(this.spinnerDelay=setTimeout(function(){this.wrapper.addClass("jBox-loading-spinner"),h.spinnerReposition&&(i?this.positionOnFadeComplete=!0:this.position()),this.spinner=jQuery(h.spinner!==!0?h.spinner:'<div class="jBox-spinner"></div>').appendTo(this.container),this.titleContainer&&"absolute"==this.spinner.css("position")&&this.spinner.css({transform:"translateY("+.5*this.titleContainer.outerHeight()+"px)"})}.bind(this),""==this.content.html()?0:h.spinnerDelay||0)),o.bind(this)()}.bind(this),h.complete=function(t){this.spinnerDelay&&clearTimeout(this.spinnerDelay),this.wrapper.removeClass("jBox-loading jBox-loading-spinner jBox-loading-spinner-delay"),this.spinner&&this.spinner.length&&this.spinner.remove()&&h.spinnerReposition&&(i?this.positionOnFadeComplete=!0:this.position()),this.ajaxLoaded=!0,e.bind(this)(t)}.bind(this),h.success=function(t){h.setContent&&this.setContent(t,!0)&&(i?this.positionOnFadeComplete=!0:this.position()),h.setContent&&this.source&&this.source.data("jBox-ajax-data",t),n.bind(this)(t)}.bind(this),h.error=function(t){a.bind(this)(t)}.bind(this),this.ajaxRequest=jQuery.ajax(h),this},jBox.prototype.audio=function(t,i){if(!t)return this;if(!jBox._audio&&(jBox._audio={}),!jBox._audio[t]){var s=jQuery("<audio/>");jQuery("<source/>",{src:t+".mp3"}).appendTo(s),jQuery("<source/>",{src:t+".ogg"}).appendTo(s),jBox._audio[t]=s[0]}jBox._audio[t].volume=Math.min((void 0!=i?i:100)/100,1);try{jBox._audio[t].pause(),jBox._audio[t].currentTime=0}catch(t){}return jBox._audio[t].play(),this},jBox._animationSpeeds={tada:1e3,tadaSmall:1e3,flash:500,shake:400,pulseUp:250,pulseDown:250,popIn:250,popOut:250,fadeIn:200,fadeOut:200,slideUp:400,slideRight:400,slideLeft:400,slideDown:400},jBox.prototype.animate=function(t,i){!i&&(i={}),!this.animationTimeout&&(this.animationTimeout={}),!i.element&&(i.element=this.wrapper),!i.element.data("jBox-animating-id")&&i.element.data("jBox-animating-id",jBox._getUniqueElementID()),i.element.data("jBox-animating")&&(i.element.removeClass(i.element.data("jBox-animating")).data("jBox-animating",null),this.animationTimeout[i.element.data("jBox-animating-id")]&&clearTimeout(this.animationTimeout[i.element.data("jBox-animating-id")])),i.element.addClass("jBox-animated-"+t).data("jBox-animating","jBox-animated-"+t),this.animationTimeout[i.element.data("jBox-animating-id")]=setTimeout(function(){i.element.removeClass(i.element.data("jBox-animating")).data("jBox-animating",null),i.complete&&i.complete()},jBox._animationSpeeds[t])},jBox.prototype.destroy=function(){return this.detach(),this.isOpen&&this.close({ignoreDelay:!0}),this.wrapper&&this.wrapper.remove(),this.overlay&&this.overlay.remove(),this._styles&&this._styles.remove(),this.isDestroyed=!0,this},jBox._getUniqueID=function(){var t=1;return function(){return t++}}(),jBox._getUniqueElementID=function(){var t=1;return function(){return t++}}(),jBox._pluginOptions={},jQuery.fn.jBox=function(t,i){return!t&&(t={}),!i&&(i={}),new jBox(t,jQuery.extend(i,{attach:this}))};jQuery(document).ready(function(){new jBoxPlugin("Notice",{color:null,stack:!0,stackSpacing:10,autoClose:6e3,attributes:{x:"right",y:"top"},position:{x:15,y:15},responsivePositions:{500:{x:5,y:5},768:{x:10,y:10}},target:window,fixed:!0,animation:"zoomIn",closeOnClick:"box",zIndex:12e3,_onInit:function(){this.defaultNoticePosition=jQuery.extend({},this.options.position),this._adjustNoticePositon=function(){var t=jQuery(window),i={x:t.width(),y:t.height()};this.options.position=jQuery.extend({},this.defaultNoticePosition),jQuery.each(this.options.responsivePositions,function(t,o){if(i.x<=t)return this.options.position=o,!1}.bind(this)),this.options.adjustDistance={top:this.options.position.y,right:this.options.position.x,bottom:this.options.position.y,left:this.options.position.x}},this.options.content instanceof jQuery&&(this.options.content=this.options.content.clone().attr("id","")),jQuery(window).on("resize.responsivejBoxNotice-"+this.id,function(t){this.isOpen&&this._adjustNoticePositon()}.bind(this)),this.open()},_onCreated:function(){this.wrapper.addClass("jBox-Notice-color jBox-Notice-"+(this.options.color||"gray")),this.wrapper.data("jBox-Notice-position",this.options.attributes.x+"-"+this.options.attributes.y)},_onOpen:function(){this._adjustNoticePositon(),jQuery.each(jQuery(".jBox-Notice"),function(t,i){if(i=jQuery(i),i.attr("id")!=this.id&&i.data("jBox-Notice-position")==this.options.attributes.x+"-"+this.options.attributes.y){if(!this.options.stack)return void i.data("jBox").close({ignoreDelay:!0});var o=(i.data("jBoxNoticeMargin")?parseInt(i.data("jBoxNoticeMargin")):parseInt(i.css("margin-"+this.options.attributes.y)))+this.wrapper.outerHeight()+this.options.stackSpacing;i.data("jBoxNoticeMargin",o),i.css("margin-"+this.options.attributes.y,o)}}.bind(this))},_onCloseComplete:function(){this.destroy()}})});!function(t,i,e,s){function o(i,e){var h=this;"object"==typeof e&&(delete e.refresh,delete e.render,t.extend(this,e)),this.$element=t(i),!this.imageSrc&&this.$element.is("img")&&(this.imageSrc=this.$element.attr("src"));var r=(this.position+"").toLowerCase().match(/\S+/g)||[];if(r.length<1&&r.push("center"),1==r.length&&r.push(r[0]),("top"==r[0]||"bottom"==r[0]||"left"==r[1]||"right"==r[1])&&(r=[r[1],r[0]]),this.positionX!=s&&(r[0]=this.positionX.toLowerCase()),this.positionY!=s&&(r[1]=this.positionY.toLowerCase()),h.positionX=r[0],h.positionY=r[1],"left"!=this.positionX&&"right"!=this.positionX&&(this.positionX=isNaN(parseInt(this.positionX))?"center":parseInt(this.positionX)),"top"!=this.positionY&&"bottom"!=this.positionY&&(this.positionY=isNaN(parseInt(this.positionY))?"center":parseInt(this.positionY)),this.position=this.positionX+(isNaN(this.positionX)?"":"px")+" "+this.positionY+(isNaN(this.positionY)?"":"px"),navigator.userAgent.match(/(iPod|iPhone|iPad)/))return this.imageSrc&&this.iosFix&&!this.$element.is("img")&&this.$element.css({backgroundImage:"url("+this.imageSrc+")",backgroundSize:"cover",backgroundPosition:this.position}),this;if(navigator.userAgent.match(/(Android)/))return this.imageSrc&&this.androidFix&&!this.$element.is("img")&&this.$element.css({backgroundImage:"url("+this.imageSrc+")",backgroundSize:"cover",backgroundPosition:this.position}),this;this.$mirror=t("<div />").prependTo("body");var a=this.$element.find(">.parallax-slider"),n=!1;0==a.length?this.$slider=t("<img />").prependTo(this.$mirror):(this.$slider=a.prependTo(this.$mirror),n=!0),this.$mirror.addClass("parallax-mirror").css({visibility:"hidden",zIndex:this.zIndex,position:"fixed",top:0,left:0,overflow:"hidden"}),this.$slider.addClass("parallax-slider").one("load",function(){h.naturalHeight&&h.naturalWidth||(h.naturalHeight=this.naturalHeight||this.height||1,h.naturalWidth=this.naturalWidth||this.width||1),h.aspectRatio=h.naturalWidth/h.naturalHeight,o.isSetup||o.setup(),o.sliders.push(h),o.isFresh=!1,o.requestRender()}),n||(this.$slider[0].src=this.imageSrc),(this.naturalHeight&&this.naturalWidth||this.$slider[0].complete||a.length>0)&&this.$slider.trigger("load")}function h(s){return this.each(function(){var h=t(this),r="object"==typeof s&&s;this==i||this==e||h.is("body")?o.configure(r):h.data("px.parallax")?"object"==typeof s&&t.extend(h.data("px.parallax"),r):(r=t.extend({},h.data(),r),h.data("px.parallax",new o(this,r))),"string"==typeof s&&("destroy"==s?o.destroy(this):o[s]())})}!function(){for(var t=0,e=["ms","moz","webkit","o"],s=0;s<e.length&&!i.requestAnimationFrame;++s)i.requestAnimationFrame=i[e[s]+"RequestAnimationFrame"],i.cancelAnimationFrame=i[e[s]+"CancelAnimationFrame"]||i[e[s]+"CancelRequestAnimationFrame"];i.requestAnimationFrame||(i.requestAnimationFrame=function(e){var s=(new Date).getTime(),o=Math.max(0,16-(s-t)),h=i.setTimeout(function(){e(s+o)},o);return t=s+o,h}),i.cancelAnimationFrame||(i.cancelAnimationFrame=function(t){clearTimeout(t)})}(),t.extend(o.prototype,{speed:.2,bleed:0,zIndex:-100,iosFix:!0,androidFix:!0,position:"center",overScrollFix:!1,refresh:function(){this.boxWidth=this.$element.outerWidth(),this.boxHeight=this.$element.outerHeight()+2*this.bleed,this.boxOffsetTop=this.$element.offset().top-this.bleed,this.boxOffsetLeft=this.$element.offset().left,this.boxOffsetBottom=this.boxOffsetTop+this.boxHeight;var t=o.winHeight,i=o.docHeight,e=Math.min(this.boxOffsetTop,i-t),s=Math.max(this.boxOffsetTop+this.boxHeight-t,0),h=this.boxHeight+(e-s)*(1-this.speed)|0,r=(this.boxOffsetTop-e)*(1-this.speed)|0;if(h*this.aspectRatio>=this.boxWidth){this.imageWidth=h*this.aspectRatio|0,this.imageHeight=h,this.offsetBaseTop=r;var a=this.imageWidth-this.boxWidth;this.offsetLeft="left"==this.positionX?0:"right"==this.positionX?-a:isNaN(this.positionX)?-a/2|0:Math.max(this.positionX,-a)}else{this.imageWidth=this.boxWidth,this.imageHeight=this.boxWidth/this.aspectRatio|0,this.offsetLeft=0;var a=this.imageHeight-h;this.offsetBaseTop="top"==this.positionY?r:"bottom"==this.positionY?r-a:isNaN(this.positionY)?r-a/2|0:r+Math.max(this.positionY,-a)}},render:function(){var t=o.scrollTop,i=o.scrollLeft,e=this.overScrollFix?o.overScroll:0,s=t+o.winHeight;this.boxOffsetBottom>t&&this.boxOffsetTop<=s?(this.visibility="visible",this.mirrorTop=this.boxOffsetTop-t,this.mirrorLeft=this.boxOffsetLeft-i,this.offsetTop=this.offsetBaseTop-this.mirrorTop*(1-this.speed)):this.visibility="hidden",this.$mirror.css({transform:"translate3d(0px, 0px, 0px)",visibility:this.visibility,top:this.mirrorTop-e,left:this.mirrorLeft,height:this.boxHeight,width:this.boxWidth}),this.$slider.css({transform:"translate3d(0px, 0px, 0px)",position:"absolute",top:this.offsetTop,left:this.offsetLeft,height:this.imageHeight,width:this.imageWidth,maxWidth:"none"})}}),t.extend(o,{scrollTop:0,scrollLeft:0,winHeight:0,winWidth:0,docHeight:1<<30,docWidth:1<<30,sliders:[],isReady:!1,isFresh:!1,isBusy:!1,setup:function(){if(!this.isReady){var s=t(e),h=t(i),r=function(){o.winHeight=h.height(),o.winWidth=h.width(),o.docHeight=s.height(),o.docWidth=s.width()},a=function(){var t=h.scrollTop(),i=o.docHeight-o.winHeight,e=o.docWidth-o.winWidth;o.scrollTop=Math.max(0,Math.min(i,t)),o.scrollLeft=Math.max(0,Math.min(e,h.scrollLeft())),o.overScroll=Math.max(t-i,Math.min(t,0))};h.on("resize.px.parallax load.px.parallax",function(){r(),o.isFresh=!1,o.requestRender()}).on("scroll.px.parallax load.px.parallax",function(){a(),o.requestRender()}),r(),a(),this.isReady=!0}},configure:function(i){"object"==typeof i&&(delete i.refresh,delete i.render,t.extend(this.prototype,i))},refresh:function(){t.each(this.sliders,function(){this.refresh()}),this.isFresh=!0},render:function(){this.isFresh||this.refresh(),t.each(this.sliders,function(){this.render()})},requestRender:function(){var t=this;this.isBusy||(this.isBusy=!0,i.requestAnimationFrame(function(){t.render(),t.isBusy=!1}))},destroy:function(e){var s,h=t(e).data("px.parallax");for(h.$mirror.remove(),s=0;s<this.sliders.length;s+=1)this.sliders[s]==h&&this.sliders.splice(s,1);t(e).data("px.parallax",!1),0===this.sliders.length&&(t(i).off("scroll.px.parallax resize.px.parallax load.px.parallax"),this.isReady=!1,o.isSetup=!1)}});var r=t.fn.parallax;t.fn.parallax=h,t.fn.parallax.Constructor=o,t.fn.parallax.noConflict=function(){return t.fn.parallax=r,this},t(e).on("ready.px.parallax.data-api",function(){t('[data-parallax="scroll"]').parallax()})}(jQuery,window,document);(function(){var a,b,c,d,e,f,g,h,i,j;b=window.device,a={},window.device=a,d=window.document.documentElement,j=window.navigator.userAgent.toLowerCase(),a.ios=function(){return a.iphone()||a.ipod()||a.ipad()},a.iphone=function(){return!a.windows()&&e("iphone")},a.ipod=function(){return e("ipod")},a.ipad=function(){return e("ipad")},a.android=function(){return!a.windows()&&e("android")},a.androidPhone=function(){return a.android()&&e("mobile")},a.androidTablet=function(){return a.android()&&!e("mobile")},a.blackberry=function(){return e("blackberry")||e("bb10")||e("rim")},a.blackberryPhone=function(){return a.blackberry()&&!e("tablet")},a.blackberryTablet=function(){return a.blackberry()&&e("tablet")},a.windows=function(){return e("windows")},a.windowsPhone=function(){return a.windows()&&e("phone")},a.windowsTablet=function(){return a.windows()&&e("touch")&&!a.windowsPhone()},a.fxos=function(){return(e("(mobile;")||e("(tablet;"))&&e("; rv:")},a.fxosPhone=function(){return a.fxos()&&e("mobile")},a.fxosTablet=function(){return a.fxos()&&e("tablet")},a.meego=function(){return e("meego")},a.cordova=function(){return window.cordova&&"file:"===location.protocol},a.nodeWebkit=function(){return"object"==typeof window.process},a.mobile=function(){return a.androidPhone()||a.iphone()||a.ipod()||a.windowsPhone()||a.blackberryPhone()||a.fxosPhone()||a.meego()},a.tablet=function(){return a.ipad()||a.androidTablet()||a.blackberryTablet()||a.windowsTablet()||a.fxosTablet()},a.desktop=function(){return!a.tablet()&&!a.mobile()},a.television=function(){var a;for(television=["googletv","viera","smarttv","internet.tv","netcast","nettv","appletv","boxee","kylo","roku","dlnadoc","roku","pov_tv","hbbtv","ce-html"],a=0;a<television.length;){if(e(television[a]))return!0;a++}return!1},a.portrait=function(){return window.innerHeight/window.innerWidth>1},a.landscape=function(){return window.innerHeight/window.innerWidth<1},a.noConflict=function(){return window.device=b,this},e=function(a){return-1!==j.indexOf(a)},g=function(a){var b;return b=new RegExp(a,"i"),d.className.match(b)},c=function(a){var b=null;g(a)||(b=d.className.replace(/^\s+|\s+$/g,""),d.className=b+" "+a)},i=function(a){g(a)&&(d.className=d.className.replace(" "+a,""))},a.ios()?a.ipad()?c("ios ipad tablet"):a.iphone()?c("ios iphone mobile"):a.ipod()&&c("ios ipod mobile"):a.android()?c(a.androidTablet()?"android tablet":"android mobile"):a.blackberry()?c(a.blackberryTablet()?"blackberry tablet":"blackberry mobile"):a.windows()?c(a.windowsTablet()?"windows tablet":a.windowsPhone()?"windows mobile":"desktop"):a.fxos()?c(a.fxosTablet()?"fxos tablet":"fxos mobile"):a.meego()?c("meego mobile"):a.nodeWebkit()?c("node-webkit"):a.television()?c("television"):a.desktop()&&c("desktop"),a.cordova()&&c("cordova"),f=function(){a.landscape()?(i("portrait"),c("landscape")):(i("landscape"),c("portrait"))},h=Object.prototype.hasOwnProperty.call(window,"onorientationchange")?"orientationchange":"resize",window.addEventListener?window.addEventListener(h,f,!1):window.attachEvent?window.attachEvent(h,f):window[h]=f,f(),"function"==typeof define&&"object"==typeof define.amd&&define.amd?define(function(){return a}):"undefined"!=typeof module&&module.exports?module.exports=a:window.device=a}).call(this);
(function (document, window, index) {
    // Index is used to keep multiple navs on the same page namespaced

    "use strict";

    var responsiveNav = function (el, options) {

        var computed = !!window.getComputedStyle;

        /**
         * getComputedStyle polyfill for old browsers
         */
        if (!computed) {
            window.getComputedStyle = function(el) {
                this.el = el;
                this.getPropertyValue = function(prop) {
                    var re = /(\-([a-z]){1})/g;
                    if (prop === "float") {
                        prop = "styleFloat";
                    }
                    if (re.test(prop)) {
                        prop = prop.replace(re, function () {
                            return arguments[2].toUpperCase();
                        });
                    }
                    return el.currentStyle[prop] ? el.currentStyle[prop] : null;
                };
                return this;
            };
        }
        /* exported addEvent, removeEvent, getChildren, setAttributes, addClass, removeClass, forEach */

        /**
         * Add Event
         * fn arg can be an object or a function, thanks to handleEvent
         * read more at: http://www.thecssninja.com/javascript/handleevent
         *
         * @param  {element}  element
         * @param  {event}    event
         * @param  {Function} fn
         * @param  {boolean}  bubbling
         */
        var addEvent = function (el, evt, fn, bubble) {
                if ("addEventListener" in el) {
                    // BBOS6 doesn't support handleEvent, catch and polyfill
                    try {
                        el.addEventListener(evt, fn, bubble);
                    } catch (e) {
                        if (typeof fn === "object" && fn.handleEvent) {
                            el.addEventListener(evt, function (e) {
                                // Bind fn as this and set first arg as event object
                                fn.handleEvent.call(fn, e);
                            }, bubble);
                        } else {
                            throw e;
                        }
                    }
                } else if ("attachEvent" in el) {
                    // check if the callback is an object and contains handleEvent
                    if (typeof fn === "object" && fn.handleEvent) {
                        el.attachEvent("on" + evt, function () {
                            // Bind fn as this
                            fn.handleEvent.call(fn);
                        });
                    } else {
                        el.attachEvent("on" + evt, fn);
                    }
                }
            },

            /**
             * Remove Event
             *
             * @param  {element}  element
             * @param  {event}    event
             * @param  {Function} fn
             * @param  {boolean}  bubbling
             */
            removeEvent = function (el, evt, fn, bubble) {
                if ("removeEventListener" in el) {
                    try {
                        el.removeEventListener(evt, fn, bubble);
                    } catch (e) {
                        if (typeof fn === "object" && fn.handleEvent) {
                            el.removeEventListener(evt, function (e) {
                                fn.handleEvent.call(fn, e);
                            }, bubble);
                        } else {
                            throw e;
                        }
                    }
                } else if ("detachEvent" in el) {
                    if (typeof fn === "object" && fn.handleEvent) {
                        el.detachEvent("on" + evt, function () {
                            fn.handleEvent.call(fn);
                        });
                    } else {
                        el.detachEvent("on" + evt, fn);
                    }
                }
            },

            /**
             * Get the children of any element
             *
             * @param  {element}
             * @return {array} Returns matching elements in an array
             */
            getChildren = function (e) {
                if (e.children.length < 1) {
                    throw new Error("The Nav container has no containing elements");
                }
                // Store all children in array
                var children = [];
                // Loop through children and store in array if child != TextNode
                for (var i = 0; i < e.children.length; i++) {
                    if (e.children[i].nodeType === 1) {
                        children.push(e.children[i]);
                    }
                }
                return children;
            },

            /**
             * Sets multiple attributes at once
             *
             * @param {element} element
             * @param {attrs}   attrs
             */
            setAttributes = function (el, attrs) {
                for (var key in attrs) {
                    el.setAttribute(key, attrs[key]);
                }
            },

            /**
             * Adds a class to any element
             *
             * @param {element} element
             * @param {string}  class
             */
            addClass = function (el, cls) {
                if (el.className.indexOf(cls) !== 0) {
                    el.className += " " + cls;
                    el.className = el.className.replace(/(^\s*)|(\s*$)/g,"");
                }
            },

            /**
             * Remove a class from any element
             *
             * @param  {element} element
             * @param  {string}  class
             */
            removeClass = function (el, cls) {
                var reg = new RegExp("(\\s|^)" + cls + "(\\s|$)");
                el.className = el.className.replace(reg, " ").replace(/(^\s*)|(\s*$)/g,"");
            },

            /**
             * forEach method that passes back the stuff we need
             *
             * @param  {array}    array
             * @param  {Function} callback
             * @param  {scope}    scope
             */
            forEach = function (array, callback, scope) {
                for (var i = 0; i < array.length; i++) {
                    callback.call(scope, i, array[i]);
                }
            };

        var nav,
            opts,
            navToggle,
            styleElement = document.createElement("style"),
            htmlEl = document.documentElement,
            hasAnimFinished,
            isMobile,
            navOpen;

        var ResponsiveNav = function (el, options) {
            var i;

            /**
             * Default options
             * @type {Object}
             */
            this.options = {
                animate: true,                    // Boolean: Use CSS3 transitions, true or false
                transition: 284,                  // Integer: Speed of the transition, in milliseconds
                label: "Menu",                    // String: Label for the navigation toggle
                insert: "before",                 // String: Insert the toggle before or after the navigation
                customToggle: "",                 // Selector: Specify the ID of a custom toggle
                closeOnNavClick: false,           // Boolean: Close the navigation when one of the links are clicked
                openPos: "relative",              // String: Position of the opened nav, relative or static
                navClass: "nav-collapse",         // String: Default CSS class. If changed, you need to edit the CSS too!
                navActiveClass: "js-nav-active",  // String: Class that is added to <html> element when nav is active
                jsClass: "js",                    // String: 'JS enabled' class which is added to <html> element
                init: function(){},               // Function: Init callback
                open: function(){},               // Function: Open callback
                close: function(){}               // Function: Close callback
            };

            // User defined options
            for (i in options) {
                this.options[i] = options[i];
            }

            // Adds "js" class for <html>
            addClass(htmlEl, this.options.jsClass);

            // Wrapper
            this.wrapperEl = el.replace("#", "");

            // Try selecting ID first
            if (document.getElementById(this.wrapperEl)) {
                this.wrapper = document.getElementById(this.wrapperEl);

                // If element with an ID doesn't exist, use querySelector
            } else if (document.querySelector(this.wrapperEl)) {
                this.wrapper = document.querySelector(this.wrapperEl);

                // If element doesn't exists, stop here.
            } else {
                throw new Error("The nav element you are trying to select doesn't exist");
            }

            // Inner wrapper
            this.wrapper.inner = getChildren(this.wrapper);

            // For minification
            opts = this.options;
            nav = this.wrapper;

            // Init
            this._init(this);
        };

        ResponsiveNav.prototype = {

            /**
             * Unattaches events and removes any classes that were added
             */
            destroy: function () {
                this._removeStyles();
                removeClass(nav, "closed");
                removeClass(nav, "opened");
                removeClass(nav, opts.navClass);
                removeClass(nav, opts.navClass + "-" + this.index);
                removeClass(htmlEl, opts.navActiveClass);
                nav.removeAttribute("style");
                nav.removeAttribute("aria-hidden");

                removeEvent(window, "resize", this, false);
                removeEvent(window, "focus", this, false);
                removeEvent(document.body, "touchmove", this, false);
                removeEvent(navToggle, "touchstart", this, false);
                removeEvent(navToggle, "touchend", this, false);
                removeEvent(navToggle, "mouseup", this, false);
                removeEvent(navToggle, "keyup", this, false);
                removeEvent(navToggle, "click", this, false);

                if (!opts.customToggle) {
                    navToggle.parentNode.removeChild(navToggle);
                } else {
                    navToggle.removeAttribute("aria-hidden");
                }
            },

            /**
             * Toggles the navigation open/close
             */
            toggle: function () {
                if (hasAnimFinished === true) {
                    if (!navOpen) {
                        this.open();
                    } else {
                        this.close();
                    }
                }
            },

            /**
             * Opens the navigation
             */
            open: function () {
                if (!navOpen) {
                    removeClass(nav, "closed");
                    addClass(nav, "opened");
                    addClass(htmlEl, opts.navActiveClass);
                    addClass(navToggle, "active");
                    nav.style.position = opts.openPos;
                    setAttributes(nav, {"aria-hidden": "false"});
                    navOpen = true;
                    opts.open();
                }
            },

            /**
             * Closes the navigation
             */
            close: function () {
                if (navOpen) {
                    addClass(nav, "closed");
                    removeClass(nav, "opened");
                    removeClass(htmlEl, opts.navActiveClass);
                    removeClass(navToggle, "active");
                    setAttributes(nav, {"aria-hidden": "true"});

                    // If animations are enabled, wait until they finish
                    if (opts.animate) {
                        hasAnimFinished = false;
                        setTimeout(function () {
                            nav.style.position = "absolute";
                            hasAnimFinished = true;
                        }, opts.transition + 10);

                        // Animations aren't enabled, we can do these immediately
                    } else {
                        nav.style.position = "absolute";
                    }

                    navOpen = false;
                    opts.close();
                }
            },

            /**
             * Resize is called on window resize and orientation change.
             * It initializes the CSS styles and height calculations.
             */
            resize: function () {

                // Resize watches navigation toggle's display state
                if (window.getComputedStyle(navToggle, null).getPropertyValue("display") !== "none") {

                    isMobile = true;
                    setAttributes(navToggle, {"aria-hidden": "false"});

                    // If the navigation is hidden
                    if (nav.className.match(/(^|\s)closed(\s|$)/)) {
                        setAttributes(nav, {"aria-hidden": "true"});
                        nav.style.position = "absolute";
                    }

                    this._createStyles();
                    this._calcHeight();
                } else {

                    isMobile = false;
                    setAttributes(navToggle, {"aria-hidden": "true"});
                    setAttributes(nav, {"aria-hidden": "false"});
                    nav.style.position = opts.openPos;
                    this._removeStyles();
                }
            },

            /**
             * Takes care of all even handling
             *
             * @param  {event} event
             * @return {type} returns the type of event that should be used
             */
            handleEvent: function (e) {
                var evt = e || window.event;

                switch (evt.type) {
                    case "touchstart":
                        this._onTouchStart(evt);
                        break;
                    case "touchmove":
                        this._onTouchMove(evt);
                        break;
                    case "touchend":
                    case "mouseup":
                        this._onTouchEnd(evt);
                        break;
                    case "click":
                        this._preventDefault(evt);
                        break;
                    case "keyup":
                        this._onKeyUp(evt);
                        break;
                    case "focus":
                    case "resize":
                        this.resize(evt);
                        break;
                }
            },

            /**
             * Initializes the widget
             */
            _init: function () {
                this.index = index++;

                addClass(nav, opts.navClass);
                addClass(nav, opts.navClass + "-" + this.index);
                addClass(nav, "closed");
                hasAnimFinished = true;
                navOpen = false;

                this._closeOnNavClick();
                this._createToggle();
                this._transitions();
                this.resize();

                /**
                 * On IE8 the resize event triggers too early for some reason
                 * so it's called here again on init to make sure all the
                 * calculated styles are correct.
                 */
                var self = this;
                setTimeout(function () {
                    self.resize();
                }, 20);

                addEvent(window, "resize", this, false);
                addEvent(window, "focus", this, false);
                addEvent(document.body, "touchmove", this, false);
                addEvent(navToggle, "touchstart", this, false);
                addEvent(navToggle, "touchend", this, false);
                addEvent(navToggle, "mouseup", this, false);
                addEvent(navToggle, "keyup", this, false);
                addEvent(navToggle, "click", this, false);

                /**
                 * Init callback here
                 */
                opts.init();
            },

            /**
             * Creates Styles to the <head>
             */
            _createStyles: function () {
                if (!styleElement.parentNode) {
                    styleElement.type = "text/css";
                    document.getElementsByTagName("head")[0].appendChild(styleElement);
                }
            },

            /**
             * Removes styles from the <head>
             */
            _removeStyles: function () {
                if (styleElement.parentNode) {
                    styleElement.parentNode.removeChild(styleElement);
                }
            },

            /**
             * Creates Navigation Toggle
             */
            _createToggle: function () {

                // If there's no toggle, let's create one
                if (!opts.customToggle) {
                    var toggle = document.createElement("a");
                    toggle.innerHTML = opts.label;
                    setAttributes(toggle, {
                        "href": "#",
                        "class": "nav-toggle"
                    });

                    // Determine where to insert the toggle
                    if (opts.insert === "after") {
                        nav.parentNode.insertBefore(toggle, nav.nextSibling);
                    } else {
                        nav.parentNode.insertBefore(toggle, nav);
                    }

                    navToggle = toggle;

                    // There is a toggle already, let's use that one
                } else {
                    var toggleEl = opts.customToggle.replace("#", "");

                    if (document.getElementById(toggleEl)) {
                        navToggle = document.getElementById(toggleEl);
                    } else if (document.querySelector(toggleEl)) {
                        navToggle = document.querySelector(toggleEl);
                    } else {
                        throw new Error("The custom nav toggle you are trying to select doesn't exist");
                    }
                }
            },

            /**
             * Closes the navigation when a link inside is clicked.
             */
            _closeOnNavClick: function () {
                if (opts.closeOnNavClick) {
                    var links = nav.getElementsByTagName("a"),
                        self = this;
                    forEach(links, function (i, el) {
                        addEvent(links[i], "click", function () {
                            if (isMobile) {
                                self.toggle();
                            }
                        }, false);
                    });
                }
            },

            /**
             * Prevents the default functionality.
             *
             * @param  {event} event
             */
            _preventDefault: function(e) {
                if (e.preventDefault) {
                    if (e.stopImmediatePropagation) {
                        e.stopImmediatePropagation();
                    }
                    e.preventDefault();
                    e.stopPropagation();
                    return false;

                    // This is strictly for old IE
                } else {
                    e.returnValue = false;
                }
            },

            /**
             * On touch start we get the location of the touch.
             *
             * @param  {event} event
             */
            _onTouchStart: function (e) {
                if (!Event.prototype.stopImmediatePropagation) {
                    this._preventDefault(e);
                }
                this.startX = e.touches[0].clientX;
                this.startY = e.touches[0].clientY;
                this.touchHasMoved = false;

                /**
                 * Remove mouseup event completely here to avoid
                 * double triggering the event.
                 */
                removeEvent(navToggle, "mouseup", this, false);
            },

            /**
             * Check if the user is scrolling instead of tapping.
             *
             * @param  {event} event
             */
            _onTouchMove: function (e) {
                if (Math.abs(e.touches[0].clientX - this.startX) > 10 ||
                    Math.abs(e.touches[0].clientY - this.startY) > 10) {
                    this.touchHasMoved = true;
                }
            },

            /**
             * On touch end toggle the navigation.
             *
             * @param  {event} event
             */
            _onTouchEnd: function (e) {
                this._preventDefault(e);
                if (!isMobile) {
                    return;
                }

                // If the user isn't scrolling
                if (!this.touchHasMoved) {

                    // If the event type is touch
                    if (e.type === "touchend") {
                        this.toggle();
                        return;

                        // Event type was click, not touch
                    } else {
                        var evt = e || window.event;

                        // If it isn't a right click, do toggling
                        if (!(evt.which === 3 || evt.button === 2)) {
                            this.toggle();
                        }
                    }
                }
            },

            /**
             * For keyboard accessibility, toggle the navigation on Enter
             * keypress too.
             *
             * @param  {event} event
             */
            _onKeyUp: function (e) {
                var evt = e || window.event;
                if (evt.keyCode === 13) {
                    this.toggle();
                }
            },

            /**
             * Adds the needed CSS transitions if animations are enabled
             */
            _transitions: function () {
                if (opts.animate) {
                    var objStyle = nav.style,
                        transition = "max-height " + opts.transition + "ms";

                    objStyle.WebkitTransition =
                        objStyle.MozTransition =
                            objStyle.OTransition =
                                objStyle.transition = transition;
                }
            },

            /**
             * Calculates the height of the navigation and then creates
             * styles which are later added to the page <head>
             */
            _calcHeight: function () {
                var savedHeight = 0;
                for (var i = 0; i < nav.inner.length; i++) {
                    savedHeight += nav.inner[i].offsetHeight;
                }

                var innerStyles = "." + opts.jsClass + " ." + opts.navClass + "-" + this.index + ".opened{max-height:" + savedHeight + "px !important} ." + opts.jsClass + " ." + opts.navClass + "-" + this.index + ".opened.dropdown-active {max-height:9999px !important}";

                if (styleElement.styleSheet) {
                    styleElement.styleSheet.cssText = innerStyles;
                } else {
                    styleElement.innerHTML = innerStyles;
                }

                innerStyles = "";
            }

        };

        /**
         * Return new Responsive Nav
         */
        return new ResponsiveNav(el, options);

    };

    window.responsiveNav = responsiveNav;

}(document, window, 0));

$(document).ready(function() {
    switch(window.location.pathname){
        case "/":
            $('#one').parallax({imageSrc: 'img/pic-min.jpg'});
            $('#three').parallax({imageSrc: 'img/pic2-min.jpg'});
            break;
        case "/join":
        case "/mraytest/musicray/buy/index.php":
            document.querySelector( "form" )
                .addEventListener( "invalid", function( event ) {
                    event.preventDefault();
                    $(":invalid")[1].focus();
                }, true );
            if(device.desktop()||device.tablet()) {
                $('#buy-form').css('border-right', '1px solid black').css('margin-right', '30px');
            }
            else {
                $('#buy-form').css('border-bottom', '1px solid black').css('margin-bottom', '20px');
            }
            $('input').change(function(){
                if($("#email").val()!==""&&$('#twiceayear').prop('checked')&&$('#agree').prop('checked')){
                    console.log("qwe");
                    $('#submit').removeClass('button').addClass('button-primary').attr('disabled',false);
                }
                else{
                    $('#submit').removeClass('button-primary').addClass('button').attr('disabled',true);
                }
            });
    }
});