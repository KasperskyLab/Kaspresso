import os
from flask import Flask
from flask import request
app = Flask(__name__)

serial = ""

@app.route("/")
def hello():
    return "Hello World!"

@app.route('/set_android_serial')
def execute_command():
    global serial
    serial = request.args.get('serial')
    return 'Serial is ok: %s' % serial

#run RAW cmd command
@app.route('/cmd')
def execute_cmd():
    cmd = request.args.get('cmd')
    p = os.popen(cmd).read()
    return """%s\n\t\r<br/>%s""" % (cmd,p)

#run ADB command
@app.route('/adb')
def execute_adb():
    adb = request.args.get('adb')
    if serial != "":
        commandToExecute = "adb -s " + serial + " " +adb
        p = os.popen(commandToExecute).read()
    else:
        commandToExecute = "adb " + adb
        p = os.popen(commandToExecute).read()
    return """%s\n\t\r<br/>%s<br/>%s""" % (adb,p,commandToExecute)
