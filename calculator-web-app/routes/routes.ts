const express2 = require('express')
const router = express2.Router()
const appController = require('../controllers/appController.js')

// Application Routes
router.get('/', appController.home)
router.get('/calc', appController.calc)

module.exports = router
