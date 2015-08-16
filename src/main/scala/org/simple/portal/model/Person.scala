package org.simple.portal.model 

import net.fwbrasil.activate.entity.Entity
import net.fwbrasil.activate.migration.Migration

class Person(var name: String) extends Entity

class CreatePersonTableMigration extends Migration {

  def timestamp = 20150817032324l
  
  def up = {
    table[Person]
      .createTable(
        _.column[String]("name"))
  }
}