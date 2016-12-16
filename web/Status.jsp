
<h1>${system} Status</h1>
Current State: ${state} <br>
Last Changed: ${lastChange}<br>
<form action="./${system}" method="post">
<button name="changeState" type="submit" value="On">On</button>
<button name="changeState" type="submit" value="Off">Off</button>
</form>