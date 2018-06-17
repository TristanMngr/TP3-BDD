<%--
  Created by IntelliJ IDEA.
  User: tristanmenager
  Date: 21/05/2018
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TP3 Welcome page</title>
    <link rel="stylesheet" type="text/css" href="assets/css/semantic.css">
    <link rel="stylesheet" type="text/css" href="assets/css/custom.css">
</head>
<body>

<div class="ui container">
    <h1 class="ui largetitle center aligned header">
        TP3
    </h1>

    <div class="ui stackable three column grid">
        <div class="column">
            <div class="ui clearing segment">
                <h2>PART I</h2>
                <h3 class="ui top attached block header">Display Department</h3>
                <form class="ui form bottom attached segment" action="/part-one" method="post">
                    <input type="hidden" value="display-department" name="dispatch">
                    <button class="ui primary button" type="submit">
                        Display department
                    </button>
                </form>

                <h3 class="ui top attached block header">Move employee to an other department</h3>
                <form class="ui form bottom attached segment" action="/part-one" method="post">
                    <div class="field">
                        <label>Employee</label>
                        <input type="text" name="employee" placeholder="employee id">
                    </div>
                    <div class="field">
                        <label>Department</label>
                        <input type="text" name="department" placeholder="department id">
                    </div>
                    <input type="hidden" value="move-employee" name="dispatch">
                    <button class="ui primary button" type="submit">Submit</button>
                </form>

                <h3 class="ui top attached block header">Display table</h3>
                <form class="ui form bottom attached segment" action="/part-one" method="post">
                    <div class="field">
                        <label>Table</label>
                        <input type="text" name="table" placeholder="table name">
                    </div>
                    <input type="hidden" value="display-table" name="dispatch">
                    <button class="ui primary button" type="submit">Submit</button>
                </form>
            </div>
        </div>

        <div class="column">
            <div class="ui clearing segment">
                <h2>PART II</h2>
                <h3 class="ui top attached block header">Find Department</h3>
                <form class="ui form bottom attached segment" action="/part-two/department" method="get">
                    <div class="field">
                        <label>Departement</label>
                        <input type="text" name="department-id" placeholder="department id">
                    </div>
                    <button class="ui primary button" type="submit">Submit</button>
                </form>

                <h3 class="ui top attached block header">Find Employee</h3>
                <form class="ui form bottom attached segment" action="/part-two/employee" method="get">
                    <div class="field">
                        <label>Employee</label>
                        <input type="text" name="employee-id" placeholder="employee id">
                    </div>
                    <button class="ui primary button" type="submit">Submit</button>
                </form>
            </div>
        </div>


        <div class="column">
            <div class="ui clearing segment">
                <h2>PART III</h2>
                <h3 class="ui top attached block header">Generate Beans</h3>
                <form class="ui form bottom attached segment" action="/part-three/generate-beans" method="post">
                    <button class="ui primary button" type="submit">
                        Generate Beans
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
