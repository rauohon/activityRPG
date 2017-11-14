<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Animated Pixel Logo</title>
      <link rel="stylesheet" href="css/style.css">
      <script src="js/common.js"></script>
      <script>
      	function game(){
      		var form=createForm("gameForm", "gameGoGo", "post");
      		createinput("hidden", "id", "${id}");
      		relationObj("gameForm", "id");
      		form.submit();
      	}
      </script>
</head>

<body>
  <section>
  <div class="content">
    <canvas height="52" width="42" class="logo"></canvas>
    <div></div>
    <input type="text" class="input" placeholder="play the game" onchange="getValue(this.value)">
    <span onClick="game()" class="arrow"></span>
    <input type="hidden" value="${id}" />
  </div>
</section>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script  src="js/index.js"></script>

</body>
</html>
