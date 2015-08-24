package org.simple.portal.model 

import net.fwbrasil.activate.entity.Entity
import net.fwbrasil.activate.migration.Migration

import org.simple.portal.PersistenceContext._

class Person(var firstName: String, var lastName: String) extends Entity

class CreatePersonTableMigration extends Migration {

  def timestamp = 20150817032324l
  
  def up = {
    table[Person]
      .createTable(
        _.column[String]("firstName"),
        _.column[String]("lastName")
        )
  }
}