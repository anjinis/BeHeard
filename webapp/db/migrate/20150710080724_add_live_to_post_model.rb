class AddLiveToPostModel < ActiveRecord::Migration
  def change
  	add_column :posts, :live, :boolean
  end
end
