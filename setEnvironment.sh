export PORT=5000;
export FORCE_LOGIN_URL=https://login.salesforce.com;
export FORCE_USER=
export FORCE_PASSWORD=
export FORCE_CLIENT_ID=
export FORCE_CLIENT_SECRET=
export FORCE_REDIRECT_URI=http://localhost:5000/oauth/callback;


heroku config:add FORCE_LOGIN_URL=https://login.salesforce.com;
heroku config:add FORCE_USER=
heroku config:add FORCE_PASSWORD=
heroku config:add FORCE_CLIENT_ID=
heroku config:add FORCE_CLIENT_SECRET=
heroku config:add FORCE_REDIRECT_URI=https://eventmapper.herokuapp.com/oauth/callback;
