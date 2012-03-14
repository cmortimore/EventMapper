export PORT=9000;
export FORCE_LOGIN_URL=https://login.salesforce.com;
export FORCE_USER=admin@db.com;
export FORCE_PASSWORD=test1234;
export FORCE_CLIENT_ID=3MVG99OxTyEMCQ3hoszdSdiXWovn6yVQxJzIm1seVx2UcDeXA3k_YKvEuoOZ6mh_QZNhGRlQ8uUXpZa_eraFK;
export FORCE_CLIENT_SECRET=6738426893104414673;
export FORCE_REDIRECT_URL=http://localhost:9000/oauth;


heroku config:add FORCE_LOGIN_URL=https://login.salesforce.com;
heroku config:add FORCE_USER=admin@db.com;
heroku config:add FORCE_PASSWORD=test1234;
heroku config:add FORCE_CLIENT_ID=3MVG99OxTyEMCQ3hoszdSdiXWovJt19y1FOPFCdYF0qHnNFzVkJ4K6yLt3XqfcHR.xMSfuieSiPuyg1A8xyck;
heroku config:add FORCE_CLIENT_SECRET=1835228312529807414;
heroku config:add FORCE_REDIRECT_URL=https://eventmapper.herokuapp.com/oauth;
