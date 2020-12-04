var cvs=document.getElementById('logo')
var ctx=cvs.getContext('2d')
ctx.font='bold 97px Algerian'
ctx.textBaseline='top'
var image=new Image()
image.src='resources/drawing/smi.gif'

image.onload=function()
{
    gradient=ctx.createLinearGradient(0,0,0,89)
    gradient.addColorStop(0,'red')
    gradient.addColorStop(1,'blue')
    ctx.fillStyle=gradient
    ctx.fillText('S   CIAL APP',0,0) 
    ctx.strokeText('S   CIAL APP',0,0) 
    ctx.drawImage(image,57,17,72,64)
} 
