const bent = require('bent')
const getJSON = bent('json')
const semverMajor = require('semver/functions/major')
const semverGt = require('semver/functions/gt')
const packageJson = require('../package.json')
const NODE_API_URL = 'https://nodejs.org/dist/index.json'

const isGrater = (a, b) => semverGt(a.version, b.version)

const getLatestReleases = (releases) =>
  releases.reduce((acc, release) => {
    const major = `v${semverMajor(release.version)}`
    const existing = acc[major]
    if (!existing || isGrater(release, existing)) {
      acc[major] = release
    }
    return acc
  }, {})

exports.dependencies = (req, res) => {
  const dependencies = Object.entries(
    packageJson.dependencies
  ).map(([key, value]) => ({ name: key, version: value }))
  res.render('dependencies.hbs', { dependencies })
}

exports.minimumSecurePage = async (req, res) => {
  const releases = await getJSON(NODE_API_URL)
  const securedReleases = releases.filter((release) => release.security)
  const minimumSecured = getLatestReleases(securedReleases)
  res.render('minimum-secure.hbs', {
    result: JSON.stringify(minimumSecured, undefined, '  ')
  })
}

exports.latestReleasesPage = async (req, res) => {
  const releases = await getJSON(NODE_API_URL)
  const latest = getLatestReleases(releases)
  res.render('latest-releases.hbs', {
    result: JSON.stringify(latest, undefined, '  ')
  })
}

exports.minimumSecure = async (req, res) => {
  try {
    res.setHeader('Content-type', 'application/json')
    const releases = await getJSON(NODE_API_URL)
    const securedReleases = releases.filter((release) => release.security)
    const minimumSecured = getLatestReleases(securedReleases)
    res.json(minimumSecured)
  } catch (error) {
    res.json({ error, message: `Unable to fetch data on ${req.route.path}` })
  }
}

exports.latestReleases = async (req, res) => {
  try {
    res.setHeader('Content-type', 'application/json')
    const releases = await getJSON(NODE_API_URL)
    res.json(getLatestReleases(releases))
  } catch (error) {
    res.json({ error, message: `Unable to fetch data on ${req.route.path}` })
  }
}

exports.calc = (req, res) => {
  let dig1 = parseInt(req.query.dig1) || 0;
  let dig2 = parseInt(req.query.dig2) || 0;
  let op = req.query.op || 'add';
  console.log(`dig1 dig2 op: ${dig1} ${dig2} ${op}`);
  var answer;
  switch(op) {
    case "add":
      answer = dig1 + dig2;
      break;
    case "subtract":
      answer = dig1 - dig2;
      break;
    case "multiply":
      answer = dig1 * dig2;
      break;
    case "divide":
      answer = dig1 / dig2;
      break;
    default:
      answer = "The answer will appear here"
      // code block
  }
  res.render('calc.hbs', {'result': answer, 'dig1': dig1, 'dig2': dig2, "op": op });
}

exports.home = (req, res) => {
  res.render('new_home.hbs')
}
