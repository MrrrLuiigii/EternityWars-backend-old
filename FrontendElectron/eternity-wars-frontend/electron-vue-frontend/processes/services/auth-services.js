const jwtDecode = require('jwt-decode');
const request = require('request');
const url = require('url');
const envVariables = require('../../env-variables');

const {apiIdentifier, auth0Domain, clientId} = envVariables;


let accessToken = null;
let profile = null;
let refreshToken = null;

const redirectUri = `http://localhost:3030/callback`;


function getAccessToken() {
  return accessToken;
}

function getProfile() {
  return profile;
}

function getAuthenticationURL() {
  return 'https://' + auth0Domain + '/authorize?' +
    'audience=' + apiIdentifier + '&' +
    'scope=openid email profile offline_access&' +
    'response_type=code&' +
    'client_id=' + clientId + '&' +
    'redirect_uri=' + redirectUri;
}

function loadTokens(callbackURL) {
  return new Promise((resolve, reject) => {
    const urlParts = url.parse(callbackURL, true);
    const query = urlParts.query;

    const exchangeOptions = {
      'grant_type': 'authorization_code',
      'client_id': clientId,
      'code': query.code,
      'redirect_uri': redirectUri,
    };

    const options = {
      method: 'POST',
      url: `https://${auth0Domain}/oauth/token`,
      headers: {
        'content-type': 'application/json'
      },
      body: JSON.stringify(exchangeOptions),
    };

    request(options, async (error, resp, body) => {
      if (error || body.error) {
        await logout();
        return reject(error || body.error);
      }

      const responseBody = JSON.parse(body);
      accessToken = responseBody.access_token;
      profile = jwtDecode(responseBody.id_token);

      resolve();
    });
  });
}

async function logout() {
  accessToken = null;
  profile = null;
}

function getLogOutUrl() {
  return `https://${auth0Domain}/v2/logout?federated`;
}

module.exports = {
  getAccessToken,
  getAuthenticationURL,
  getLogOutUrl,
  getProfile,
  loadTokens,
  logout,
};