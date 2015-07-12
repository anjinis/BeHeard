class CreatePosts < ActiveRecord::Migration
  def change
    create_table :posts do |t|
      t.text :message
      t.float :lat
      t.float :long
      t.integer :severity
      t.integer :rating

      t.timestamps null: false
    end
  end
end
