from flask import Flask, request, jsonify, render_template

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/add', methods=['POST'])
def add():
    try:
        num1 = int(request.form.get('num1'))
        num2 = int(request.form.get('num2'))
        result = num1 + num2
        return render_template('index.html', result=result, num1=num1, num2=num2)
    except (TypeError, ValueError):
        error = "Invalid input. Please enter valid integers."
        return render_template('index.html', error=error)

if __name__ == '__main__':
    app.run(debug=True)
