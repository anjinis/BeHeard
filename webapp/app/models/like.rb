class Like < ActiveRecord::Base
	belongs_to :post
	# belongs_to :user
	# need to create model for user and scope for post_id as well
end
