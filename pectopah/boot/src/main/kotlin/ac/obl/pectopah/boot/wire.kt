package ac.obl.pectopah.boot

import ac.obl.pectopah.officeApi
import ac.obl.pectopah.repo.repoApiDb

// application wiring, uses provided implementations

private val repo = repoApiDb()
val office = officeApi(repo)
