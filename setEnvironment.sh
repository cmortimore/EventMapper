export PORT=5000;
export FORCE_LOGIN_URL=https://login.salesforce.com;
export FORCE_USER=puser@db.com;
export FORCE_PASSWORD=123456;
export FORCE_CLIENT_ID=3MVG99OxTyEMCQ3hoszdSdiXWovn6yVQxJzIm1seVx2UcDeXA3k_YKvEuoOZ6mh_QZNhGRlQ8uUXpZa_eraFK;
export FORCE_CLIENT_SECRET=6738426893104414673;
export FORCE_REDIRECT_URI=http://localhost:5000/oauth;


heroku config:add FORCE_LOGIN_URL=https://login.salesforce.com;
heroku config:add FORCE_USER=puser@db.com;
heroku config:add FORCE_PASSWORD=123456;
heroku config:add FORCE_CLIENT_ID=3MVG99OxTyEMCQ3hoszdSdiXWovJt19y1FOPFCdYF0qHnNFzVkJ4K6yLt3XqfcHR.xMSfuieSiPuyg1A8xyck;
heroku config:add FORCE_CLIENT_SECRET=1835228312529807414;
heroku config:add FORCE_REDIRECT_URI=https://eventmapper.herokuapp.com/oauth;
